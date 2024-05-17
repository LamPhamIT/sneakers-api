package com.example.sneaker.model.request.order;

import com.example.sneaker.model.enums.StatusCode;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.annotations.NotNull;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderChangeInforRequest implements Serializable {
    private static final Long serialVersionUID = 1L;

    @NotNull
    private UUID id;

    private Boolean isPayed;

    private StatusCode status;

    private String phoneNumber;

    private String address;

    private String fullName;

    private String email;

}
