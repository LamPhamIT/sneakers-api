package com.example.sneaker.mapper.category;


import com.example.sneaker.model.entity.Category;
import com.example.sneaker.model.request.category.CategoryRequest;
import com.example.sneaker.model.response.category.CategoryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toDto(Category entity);
    Category toEntity(CategoryRequest categoryRequest);
}
