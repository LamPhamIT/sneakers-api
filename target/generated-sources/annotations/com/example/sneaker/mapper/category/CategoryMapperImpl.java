package com.example.sneaker.mapper.category;

import com.example.sneaker.model.entity.Category;
import com.example.sneaker.model.request.category.CategoryRequest;
import com.example.sneaker.model.response.category.CategoryResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-02T02:09:34+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Private Build)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryResponse toDto(Category entity) {
        if ( entity == null ) {
            return null;
        }

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setName( entity.getName() );
        categoryResponse.setSlug( entity.getSlug() );

        return categoryResponse;
    }

    @Override
    public Category toEntity(CategoryRequest categoryRequest) {
        if ( categoryRequest == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.name( categoryRequest.getName() );
        category.slug( categoryRequest.getSlug() );

        return category.build();
    }
}
