package com.example.sneaker.model.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PasswordResetRequest {
    private String resetKey;
    @NotBlank
    @Size(min = 6, max = 100)
    private String newPassword;
}
