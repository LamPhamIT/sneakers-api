package com.example.sneaker.service.category;

import com.example.sneaker.model.response.category.CategoryResponse;

import java.util.List;

public interface ICategoryService {

    List<CategoryResponse> findAll();
}
