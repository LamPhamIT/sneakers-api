package com.example.sneaker.service.cart;

import com.example.sneaker.framework.message.model.Message;
import com.example.sneaker.model.request.cart.CartRequest;
import com.example.sneaker.model.response.cart.CartResponse;

import java.util.List;
import java.util.Optional;

public interface ICartService {
    Optional<CartResponse> saveCart(CartRequest cartRequest);
    List<CartResponse> getCart();

    void updateCart(CartRequest cartRequest);

    void deleteCart(Long id);
}
