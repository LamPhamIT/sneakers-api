package com.example.sneaker.service.category;

import com.example.sneaker.mapper.category.CategoryMapper;
import com.example.sneaker.model.response.category.CategoryResponse;
import com.example.sneaker.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService{

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryResponse> findAll() {
        List<CategoryResponse> categories = categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .toList();
        return categories;
    }
}
