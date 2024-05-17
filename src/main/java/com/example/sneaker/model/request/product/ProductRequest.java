package com.example.sneaker.model.request.product;

import com.example.sneaker.framework.base.BaseDTO;
import com.example.sneaker.model.request.branch.BranchRequest;
import com.example.sneaker.model.request.category.CategoryRequest;
import com.example.sneaker.model.request.size.SizeRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest extends BaseDTO {
    private static final Long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String thumbnail;

    @NotNull
    private String desciption;

    @NotNull
    private String imageDesc;

    @NotNull
    private Long price;

    @NotNull
    private Integer discountPercent;

    @NotNull
    private String slug;

    @NotNull
    private CategoryRequest category;

    @NotNull
    private BranchRequest branch;

    @NotNull
    @NotEmpty
    private List<SizeRequest> sizes;

}
