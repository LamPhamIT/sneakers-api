package com.example.sneaker.model.request.category;

import com.example.sneaker.framework.base.BaseDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest extends BaseDTO {
    private static final Long serialVersionUID = 1L;

    @NotEmpty
    private String name;

    @NotEmpty
    private String slug;
}
