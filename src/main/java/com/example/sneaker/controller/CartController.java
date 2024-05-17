package com.example.sneaker.controller;

import com.example.sneaker.framework.handle.model.ResponseData;
import com.example.sneaker.framework.message.MessageHelper;
import com.example.sneaker.framework.message.model.Message;
import com.example.sneaker.framework.support.ResponseSupport;
import com.example.sneaker.model.request.cart.CartRequest;
import com.example.sneaker.service.cart.ICartService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/cart")
public class CartController {
    private final Logger log = LoggerFactory.getLogger(CartController.class);

    private final ICartService cartService;

    private final ResponseSupport responseSupport;

    public CartController(ICartService cartService, ResponseSupport responseSupport) {
        this.cartService = cartService;
        this.responseSupport = responseSupport;
    }


    @PostMapping
    public ResponseEntity<ResponseData> saveCart(@Valid @RequestBody CartRequest cartRequest) {
        return responseSupport.success(ResponseData.builder().data(cartService.saveCart(cartRequest)).build());
    }

    @GetMapping
    public ResponseEntity<ResponseData> getCart() {
        return responseSupport.success(ResponseData.builder().data(cartService.getCart()).build());
    }

    @PatchMapping
    public void updateCart(@Valid @RequestBody CartRequest cartRequest) {
        cartService.updateCart(cartRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return responseSupport.success(MessageHelper.getMessage(Message.Keys.I0001),
                ResponseData.builder().build());

    }
}
