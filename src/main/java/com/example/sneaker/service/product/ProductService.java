package com.example.sneaker.service.product;

import com.example.sneaker.framework.exception.BadRequestException;
import com.example.sneaker.framework.exception.ResourceNotFoundException;
import com.example.sneaker.framework.message.MessageHelper;
import com.example.sneaker.framework.message.model.Message;
import com.example.sneaker.mapper.branch.BranchMapper;
import com.example.sneaker.mapper.category.CategoryMapper;
import com.example.sneaker.mapper.product.ProductMapper;
import com.example.sneaker.model.entity.Branch;
import com.example.sneaker.model.entity.Category;
import com.example.sneaker.model.entity.Product;
import com.example.sneaker.model.entity.Size;
import com.example.sneaker.model.request.page.PageableCreator;
import com.example.sneaker.model.request.page.PaginationRequest;
import com.example.sneaker.model.request.product.ProductRequest;
import com.example.sneaker.model.response.page.PageResponse;
import com.example.sneaker.model.response.product.ProductResponse;
import com.example.sneaker.repository.BranchRepository;
import com.example.sneaker.repository.CategoryRepository;
import com.example.sneaker.repository.ProductRepository;
import com.example.sneaker.repository.SizeRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;
    private final CategoryRepository categoryRepository;
    private final SizeRepository sizeRepository;

    private final BranchMapper branchMapper;

    private final ProductMapper productMapper;

    private final CategoryMapper categoryMapper;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          BranchRepository branchRepository,
                          CategoryRepository categoryRepository,
                          SizeRepository sizeRepository, BranchMapper branchMapper,
                          ProductMapper productMapper, CategoryMapper categoryMapper) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
        this.categoryRepository = categoryRepository;
        this.sizeRepository = sizeRepository;
        this.branchMapper = branchMapper;
        this.productMapper = productMapper;
        this.categoryMapper = categoryMapper;
    }

    @Override
    @Transactional
    public Message saveProduct(ProductRequest productRequest) {
        Product product = productMapper.toEntity(productRequest);
        Optional<Branch> branch = branchRepository.findBranchBySlug(product.getBranch().getSlug());
        Optional<Category> category = categoryRepository.findCategoryBySlug(product.getCategory().getSlug());
        if (!branch.isPresent() || !category.isPresent()) {
            throw new BadRequestException(MessageHelper.getMessage(Message.Keys.E0013));
        }
        product.setBranch(branch.get());
        product.setCategory(category.get());
        product.setIsDeleted(false);
        productRepository.save(product);
        product.getSizes().stream()
                .forEach(size -> {
                    size.setProduct(product);
                    sizeRepository.save(size);
                });

        return MessageHelper.getMessage(Message.Keys.I0004, "Product");
    }

    @Override
    public ProductResponse findProductBySlug(String slug) {
        Optional<Product> product = productRepository.findProductBySlugAndIsDeletedIsFalse(slug);
        if (!product.isPresent()) {
            throw new ResourceNotFoundException(MessageHelper.getMessage(Message.Keys.E0014, "Product"));
        }
        return productMapper.toDto(product.get());
    }

    @Override
    public PageResponse findProduct(String query, PaginationRequest paginationRequest) {
        Pageable pageable = PageableCreator.createPageable(paginationRequest);
        Page<Product> page;
        if (query == null)
            page = productRepository.findAllByIsDeletedIsFalse(pageable);
        else
            page = productRepository.findAllByQuery(query, pageable);


        List<ProductResponse> responseData = page.getContent().stream().map(productMapper::toDto).toList();
        return new PageResponse(
                page.getNumber() + 1,
                page.getNumberOfElements(),
                page.getTotalPages(),
                paginationRequest.getSortBy(),
                paginationRequest.getSortDimension(),
                responseData
        );
    }

    @Override
    public PageResponse findProductByCategory(String categorySlug, PaginationRequest paginationRequest) {
        Optional<Category> category = categoryRepository.findCategoryBySlug(categorySlug);
        if (!category.isPresent()) {
            throw new ResourceNotFoundException(MessageHelper.getMessage(Message.Keys.E0014, "Category"));
        }

        Pageable pageable = PageableCreator.createPageable(paginationRequest);
        Page<Product> page = productRepository.findAllByCategoryIdAndIsDeletedIsFalse(category.get().getId(), pageable);
        List<ProductResponse> responseData = page.getContent().stream().map(productMapper::toDto).toList();
        return new PageResponse(
                page.getNumber() + 1,
                page.getNumberOfElements(),
                page.getTotalPages(),
                paginationRequest.getSortBy(),
                paginationRequest.getSortDimension(),
                responseData
        );
    }

    @Override
    public PageResponse findProductByBranch(String branchSlug, PaginationRequest paginationRequest) {
        Optional<Branch> branch = branchRepository.findBranchBySlug(branchSlug);
        if (!branch.isPresent()) {
            throw new ResourceNotFoundException(MessageHelper.getMessage(Message.Keys.E0014, "Branch"));
        }

        Pageable pageable = PageableCreator.createPageable(paginationRequest);
        Page<Product> page = productRepository.findAllByBranchIdAndIsDeletedIsFalse(branch.get().getId(), pageable);
        List<ProductResponse> responseData = page.getContent().stream().map(productMapper::toDto).toList();
        return new PageResponse(
                page.getNumber() + 1,
                page.getNumberOfElements(),
                page.getTotalPages(),
                paginationRequest.getSortBy(),
                paginationRequest.getSortDimension(),
                responseData
        );
    }

    @Override
    public ProductResponse updateProduct(ProductRequest productRequest) {
        if (productRequest.getId() == null)
            throw new BadRequestException(MessageHelper.getMessage(Message.Keys.I0002));
        Optional<Product> product = productRepository.findById(productRequest.getId());
        if (!product.isPresent())
            throw new ResourceNotFoundException(MessageHelper.getMessage(Message.Keys.E0023, "product", productRequest.getId()));

        Product productUpdated = productMapper.toEntity(productRequest);

        Optional<Category> category = categoryRepository.findCategoryBySlug(productUpdated.getCategory().getSlug());


        Optional<Branch> branch = branchRepository.findBranchBySlug(productUpdated.getBranch().getSlug());
        if (!branch.isPresent()) {
            throw new BadRequestException(MessageHelper.getMessage(Message.Keys.E0021, "branch"));
        }

        if (productRequest.getName() != null) product.get().setName(productUpdated.getName());
        if (productRequest.getSlug() != null) product.get().setSlug(productUpdated.getSlug());
        if (productRequest.getThumbnail() != null) product.get().setThumbnail(productUpdated.getThumbnail());
        if (productRequest.getDesciption() != null) product.get().setDesciption(productUpdated.getDesciption());
        if (productRequest.getPrice() != null) product.get().setPrice(productUpdated.getPrice());
        if (productRequest.getImageDesc() != null) product.get().setImageDesc(productUpdated.getImageDesc());
        if (productRequest.getDiscountPercent() != null)
            product.get().setDiscountPercent(productRequest.getDiscountPercent());
        if (productRequest.getBranch() != null) product.get().setCategory(
                category.orElseThrow(() -> new BadRequestException(MessageHelper.getMessage(Message.Keys.E0021, "branch"))));
        if (productRequest.getCategory() != null) product.get().setBranch(
                branch.orElseThrow(() -> new BadRequestException(MessageHelper.getMessage(Message.Keys.E0021, "branch"))));
        productRepository.save(product.get());
        if (productRequest.getSizes() != null) {
            for (Size size : productUpdated.getSizes()) {
                boolean flag = false;
                for (Size sizeOrigin : product.get().getSizes()) {
                    if (size.getSize().equals(sizeOrigin.getSize())) {
                        flag = false;
                        sizeOrigin.setQuantity(size.getQuantity());
                        break;
                    } else {
                        flag = true;
                    }
                }
                if (flag) {
                    size.setProduct(product.get());
                    product.get().getSizes().add(size);
                }
            }
        }
        List<Size> sizes = product.get().getSizes().stream().map(
                sizeRepository::save
        ).toList();
        productUpdated.setSizes(sizes);

        return productMapper.toDto(productUpdated);
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new ResourceNotFoundException(MessageHelper.getMessage(Message.Keys.E0021, "product", id));
        }
        product.get().setIsDeleted(true);
        productRepository.save(product.get());
    }

}
