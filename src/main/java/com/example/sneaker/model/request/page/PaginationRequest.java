package com.example.sneaker.model.request.page;

import com.example.sneaker.framework.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.java.Log;

@Data
@AllArgsConstructor
public class PaginationRequest extends BaseDTO {

    private static final Long serialVersionUID = 1L;

    private Integer page;

    private Integer limit;

    private String sortBy;

    private String sortDimension;
}
