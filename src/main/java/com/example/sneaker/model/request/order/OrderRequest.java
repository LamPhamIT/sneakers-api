package com.example.sneaker.model.request.order;

import com.example.sneaker.framework.base.BaseDTO;
import com.example.sneaker.model.enums.StatusCode;
import com.example.sneaker.model.request.cart.CartRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest extends BaseDTO {

    private static final Long serialVersionUID = 1L;

    @NotNull
    private Long total;

    @NotNull
    private Boolean isPayed;

    @NotEmpty
    private List<CartRequest> carts = new ArrayList<>();

    @NotNull
    private StatusCode status;

    @NotBlank()
    private String phoneNumber;

    @NotBlank()
    private String address;

    @NotBlank()
    private String fullName;

    @NotBlank
    private String email;

}
