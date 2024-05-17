package com.example.sneaker.model.response.cart;

import com.example.sneaker.framework.base.BaseDTO;
import com.example.sneaker.model.response.product.ProductResponse;
import lombok.Data;

@Data
public class CartResponse extends BaseDTO {
    private static final Long serialVersionUUID = 1L;

    private Long id;

    private Long quantity;

    private Long size;

    private ProductResponse product;

}
