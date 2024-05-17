package com.example.sneaker.model.request.user;

import com.example.sneaker.model.response.user.UserResponse;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterUserRequest extends UserResponse {

    private static final long serialVersionUID = 1L;

    public static final int PASSWORD_MIN_LENGTH = 6;

    public static final int PASSWORD_MAX_LENGTH = 100;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

}
