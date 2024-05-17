package com.example.sneaker.mapper.cart;

import com.example.sneaker.model.entity.Cart;
import com.example.sneaker.model.request.cart.CartRequest;
import com.example.sneaker.model.response.cart.CartResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper  {
    Cart toEntity(CartRequest cartRequest);

    CartResponse toDto(Cart cart);
}
