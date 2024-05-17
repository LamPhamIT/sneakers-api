package com.example.sneaker.model.response.size;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SizeResponse {

    @NotNull
    private Long size;

    @NotNull
    private Long quantity;
}
