package com.example.sneaker.model.request.authenticate;

import com.example.sneaker.framework.base.BaseDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenRequest extends BaseDTO {

    private static final Long serialVersionUID = 1L;

    @NotBlank()
    private String refreshToken;
}
