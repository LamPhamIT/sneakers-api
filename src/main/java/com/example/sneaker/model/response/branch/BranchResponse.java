package com.example.sneaker.model.response.branch;

import com.example.sneaker.framework.base.BaseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BranchResponse extends BaseDTO {
    private static final Long serialVersionUUID = 1L;

    @NotNull
    @NotBlank()
    private String name;

    @NotNull
    @NotBlank()
    private String slug;
}
