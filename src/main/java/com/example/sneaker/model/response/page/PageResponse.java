package com.example.sneaker.model.response.page;

import com.example.sneaker.framework.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageResponse extends BaseDTO {
    private static final Long serialVersionUID = 1L;

    private Integer page;

    private long limit;

    private long totalPages;

    private String sortBy;

    private String sortDimension;

    private Object responseData;
}
