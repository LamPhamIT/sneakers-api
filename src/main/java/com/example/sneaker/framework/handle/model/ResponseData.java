package com.example.sneaker.framework.handle.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData {

    private Boolean isSuccess;
    private String message;
    private List<FieldError> errors;
    private Object data;
}
