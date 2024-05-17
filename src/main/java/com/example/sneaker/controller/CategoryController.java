package com.example.sneaker.controller;

import com.example.sneaker.framework.handle.model.ResponseData;
import com.example.sneaker.framework.message.MessageHelper;
import com.example.sneaker.framework.message.model.Message;
import com.example.sneaker.framework.support.ResponseSupport;
import com.example.sneaker.service.category.ICategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final ICategoryService categoryService;

    private final ResponseSupport responseSupport;

    public CategoryController(ICategoryService categoryService, ResponseSupport responseSupport) {
        this.categoryService = categoryService;
        this.responseSupport = responseSupport;
    }

    @GetMapping
    public ResponseEntity<ResponseData> getAll() {
        return responseSupport.success(MessageHelper.getMessage(Message.Keys.I0001),
                ResponseData.builder().data(categoryService.findAll()).build());
    }
}
