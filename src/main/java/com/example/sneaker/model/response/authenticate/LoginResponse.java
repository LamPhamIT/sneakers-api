package com.example.sneaker.model.response.authenticate;

import com.example.sneaker.framework.base.BaseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse extends BaseDTO {

    private final static Long serialVersionUID = 1L;

    private String accessToken;

    private String refreshToken;

}
