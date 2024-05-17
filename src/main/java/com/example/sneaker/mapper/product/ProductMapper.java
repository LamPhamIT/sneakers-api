package com.example.sneaker.mapper.product;

import com.example.sneaker.model.entity.Product;
import com.example.sneaker.model.request.product.ProductRequest;
import com.example.sneaker.model.response.product.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductRequest productRequest);
    ProductResponse toDto(Product entity);
}
