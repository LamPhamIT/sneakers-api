package com.example.sneaker.model.request.size;

import com.example.sneaker.framework.base.BaseDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SizeRequest extends BaseDTO {
    private static final Long serialVersionUID = 1L;

    @NotNull
    private Long size;

    @NotNull
    private Long quantity;

}
