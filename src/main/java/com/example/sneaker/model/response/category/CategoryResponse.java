package com.example.sneaker.model.response.category;

import com.example.sneaker.framework.base.BaseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryResponse extends BaseDTO {

    private static final Long serialVersionUID = 1L;

    @NotNull
    @NotBlank()
    private String name;

    @NotNull
    @NotBlank()
    private String slug;
}
