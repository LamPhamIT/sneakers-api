package com.example.sneaker.model.response.product;

import com.example.sneaker.framework.base.BaseDTO;
import com.example.sneaker.model.response.branch.BranchResponse;
import com.example.sneaker.model.response.category.CategoryResponse;
import com.example.sneaker.model.response.size.SizeResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductResponse extends BaseDTO {
    private static final Long serialVersionUID = 1L;

    @NotNull
    private Long id;

    @NotBlank()
    private String name;

    @NotBlank()
    private String thumbnail;

    @NotBlank()
    private String desciption;

    private String imageDesc;

    @NotNull
    private Integer discountPercent;


    @NotNull
    private Long price;

    @NotBlank()
    private String slug;

    @NotNull
    private CategoryResponse category;

    @NotNull
    private BranchResponse branch;

    @NotEmpty
    private List<SizeResponse> sizes = new ArrayList<>();

}
