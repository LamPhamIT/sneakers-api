package com.example.sneaker.service.product;

import com.example.sneaker.framework.message.model.Message;
import com.example.sneaker.model.request.page.PaginationRequest;
import com.example.sneaker.model.request.product.ProductRequest;
import com.example.sneaker.model.response.page.PageResponse;
import com.example.sneaker.model.response.product.ProductResponse;
public interface IProductService {
    public Message saveProduct(ProductRequest productRequest);
    public ProductResponse findProductBySlug(String slug);

    public PageResponse findProduct(String query, PaginationRequest paginationRequest);

    public PageResponse findProductByCategory(String categorySlug, PaginationRequest paginationRequest);

    PageResponse findProductByBranch(String branchSlug, PaginationRequest paginationRequest);

    ProductResponse updateProduct(ProductRequest productRequest);

    void  deleteProduct(Long id);


}
