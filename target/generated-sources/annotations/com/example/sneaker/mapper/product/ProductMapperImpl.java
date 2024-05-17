package com.example.sneaker.mapper.product;

import com.example.sneaker.model.entity.Branch;
import com.example.sneaker.model.entity.Category;
import com.example.sneaker.model.entity.Product;
import com.example.sneaker.model.entity.Size;
import com.example.sneaker.model.request.branch.BranchRequest;
import com.example.sneaker.model.request.category.CategoryRequest;
import com.example.sneaker.model.request.product.ProductRequest;
import com.example.sneaker.model.request.size.SizeRequest;
import com.example.sneaker.model.response.branch.BranchResponse;
import com.example.sneaker.model.response.category.CategoryResponse;
import com.example.sneaker.model.response.product.ProductResponse;
import com.example.sneaker.model.response.size.SizeResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-02T02:09:34+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Private Build)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toEntity(ProductRequest productRequest) {
        if ( productRequest == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( productRequest.getId() );
        product.name( productRequest.getName() );
        product.thumbnail( productRequest.getThumbnail() );
        product.desciption( productRequest.getDesciption() );
        product.imageDesc( productRequest.getImageDesc() );
        product.price( productRequest.getPrice() );
        product.slug( productRequest.getSlug() );
        product.discountPercent( productRequest.getDiscountPercent() );
        product.category( categoryRequestToCategory( productRequest.getCategory() ) );
        product.branch( branchRequestToBranch( productRequest.getBranch() ) );
        product.sizes( sizeRequestListToSizeList( productRequest.getSizes() ) );

        return product.build();
    }

    @Override
    public ProductResponse toDto(Product entity) {
        if ( entity == null ) {
            return null;
        }

        ProductResponse productResponse = new ProductResponse();

        productResponse.setId( entity.getId() );
        productResponse.setName( entity.getName() );
        productResponse.setThumbnail( entity.getThumbnail() );
        productResponse.setDesciption( entity.getDesciption() );
        productResponse.setImageDesc( entity.getImageDesc() );
        productResponse.setDiscountPercent( entity.getDiscountPercent() );
        productResponse.setPrice( entity.getPrice() );
        productResponse.setSlug( entity.getSlug() );
        productResponse.setCategory( categoryToCategoryResponse( entity.getCategory() ) );
        productResponse.setBranch( branchToBranchResponse( entity.getBranch() ) );
        productResponse.setSizes( sizeListToSizeResponseList( entity.getSizes() ) );

        return productResponse;
    }

    protected Category categoryRequestToCategory(CategoryRequest categoryRequest) {
        if ( categoryRequest == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.name( categoryRequest.getName() );
        category.slug( categoryRequest.getSlug() );

        return category.build();
    }

    protected Branch branchRequestToBranch(BranchRequest branchRequest) {
        if ( branchRequest == null ) {
            return null;
        }

        Branch branch = new Branch();

        branch.setName( branchRequest.getName() );
        branch.setSlug( branchRequest.getSlug() );

        return branch;
    }

    protected Size sizeRequestToSize(SizeRequest sizeRequest) {
        if ( sizeRequest == null ) {
            return null;
        }

        Size size = new Size();

        size.setSize( sizeRequest.getSize() );
        size.setQuantity( sizeRequest.getQuantity() );

        return size;
    }

    protected List<Size> sizeRequestListToSizeList(List<SizeRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<Size> list1 = new ArrayList<Size>( list.size() );
        for ( SizeRequest sizeRequest : list ) {
            list1.add( sizeRequestToSize( sizeRequest ) );
        }

        return list1;
    }

    protected CategoryResponse categoryToCategoryResponse(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setName( category.getName() );
        categoryResponse.setSlug( category.getSlug() );

        return categoryResponse;
    }

    protected BranchResponse branchToBranchResponse(Branch branch) {
        if ( branch == null ) {
            return null;
        }

        BranchResponse branchResponse = new BranchResponse();

        branchResponse.setName( branch.getName() );
        branchResponse.setSlug( branch.getSlug() );

        return branchResponse;
    }

    protected SizeResponse sizeToSizeResponse(Size size) {
        if ( size == null ) {
            return null;
        }

        SizeResponse sizeResponse = new SizeResponse();

        sizeResponse.setSize( size.getSize() );
        sizeResponse.setQuantity( size.getQuantity() );

        return sizeResponse;
    }

    protected List<SizeResponse> sizeListToSizeResponseList(List<Size> list) {
        if ( list == null ) {
            return null;
        }

        List<SizeResponse> list1 = new ArrayList<SizeResponse>( list.size() );
        for ( Size size : list ) {
            list1.add( sizeToSizeResponse( size ) );
        }

        return list1;
    }
}
