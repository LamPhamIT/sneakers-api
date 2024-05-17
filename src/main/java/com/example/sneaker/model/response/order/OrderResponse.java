package com.example.sneaker.model.response.order;

import com.example.sneaker.framework.base.BaseDTO;
import com.example.sneaker.model.entity.Product;
import com.example.sneaker.model.enums.StatusCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse extends BaseDTO {
    private static final Long serialVersionUID = 1L;

    private UUID id;

    private Long total;

    private Boolean isPayed;

    private List<Product> products = new ArrayList<>();

    private StatusCode status;

    private String phoneNumber;

    private String address;

    private String fullName;

    private String email;

}
