package com.example.sneaker.model.request.authenticate;

import com.example.sneaker.framework.base.BaseDTO;
import com.example.sneaker.model.request.user.RegisterUserRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest extends BaseDTO {

    private static final Long serialVersionUID = 1L;

    @Email(regexp = ".+[@].+[\\.].+")
    @NotNull
    private String email;

    @NotNull
    @Size(min = RegisterUserRequest.PASSWORD_MIN_LENGTH, max = RegisterUserRequest.PASSWORD_MAX_LENGTH)
    private String password;

}
