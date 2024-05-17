package com.example.sneaker.model.request.cart;

import com.example.sneaker.framework.base.BaseDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import software.amazon.awssdk.annotations.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest extends BaseDTO {
    private static final Long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private Long quantity;

    @NotNull
    private Long size;

    @NotBlank()
    private String productSlug;
}
