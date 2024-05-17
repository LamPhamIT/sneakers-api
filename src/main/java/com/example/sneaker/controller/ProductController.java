package com.example.sneaker.controller;

import com.example.sneaker.framework.enums.DirectionSortEnum;
import com.example.sneaker.framework.handle.model.ResponseData;
import com.example.sneaker.framework.message.MessageHelper;
import com.example.sneaker.framework.message.model.Message;
import com.example.sneaker.framework.support.ResponseSupport;
import com.example.sneaker.model.request.page.PaginationRequest;
import com.example.sneaker.model.request.product.ProductRequest;
import com.example.sneaker.service.product.IProductService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin
public class ProductController {
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ResponseSupport responseSupport;
    private final IProductService productService;

    @Autowired
    public ProductController(ResponseSupport responseSupport, IProductService productService) {
        this.responseSupport = responseSupport;
        this.productService = productService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseData> saveProduct(@Valid @RequestBody ProductRequest productRequest) {
        return responseSupport.success(
                productService.saveProduct(productRequest)
        );
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ResponseData> getProductBySlug(@PathVariable String slug) {
        return responseSupport.success(ResponseData.builder().data(productService.findProductBySlug(slug)).build());
    }

    @GetMapping
    public ResponseEntity<ResponseData> findProducts(
            @RequestParam(name = "query", required = false) String query,
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "limit", required = false) Integer limit,
            @RequestParam(name = "sortBy", required = false) String sortBy,
            @RequestParam(name = "sortDimension", required = false) DirectionSortEnum direction
    ) {
        PaginationRequest paginationRequest = new PaginationRequest(page, limit, sortBy, (direction == null) ? null : direction.name());
        return responseSupport.success(ResponseData.builder().data(productService.findProduct(query, paginationRequest)).build());
    }

    @GetMapping("/category/{categorySlug}")
    public ResponseEntity<ResponseData> findProduct(@PathVariable String categorySlug,
                                                    @RequestParam(name = "page", required = false) Integer page,
                                                    @RequestParam(name = "limit", required = false) Integer limit,
                                                    @RequestParam(name = "sortBy", required = false) String sortBy,
                                                    @RequestParam(name = "sortDimension", required = false) DirectionSortEnum direction) {
        PaginationRequest paginationRequest = new PaginationRequest(page, limit, sortBy, (direction == null) ? null : direction.name());
        return responseSupport.success(
                ResponseData.builder()
                        .data(productService.findProductByCategory(categorySlug, paginationRequest))
                        .build()
        );
    }

    @GetMapping("/branch/{branchSlug}")
    public ResponseEntity<ResponseData> findProductByBranchSlug(@PathVariable String branchSlug,
                                                                @RequestParam(name = "page", required = false) Integer page,
                                                                @RequestParam(name = "limit", required = false) Integer limit,
                                                                @RequestParam(name = "sortBy", required = false) String sortBy,
                                                                @RequestParam(name = "sortDimension", required = false) DirectionSortEnum direction
    ) {

        PaginationRequest paginationRequest = new PaginationRequest(page, limit, sortBy, (direction == null) ? null : direction.name());
        return responseSupport.success(
                ResponseData.builder()
                        .data(productService.findProductByBranch(branchSlug, paginationRequest))
                        .build()
        );
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseData> updateProduct(@RequestBody ProductRequest productRequest) {
        return responseSupport.success(MessageHelper.getMessage(Message.Keys.I0002),
                ResponseData.builder().data(productService.updateProduct(productRequest)).build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseData> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return responseSupport.success(MessageHelper.getMessage(Message.Keys.I0001),
                ResponseData.builder().build());
    }
}
