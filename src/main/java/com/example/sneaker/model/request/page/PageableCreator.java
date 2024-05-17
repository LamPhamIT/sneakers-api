package com.example.sneaker.model.request.page;

import com.example.sneaker.framework.exception.BadRequestException;
import com.example.sneaker.framework.message.MessageHelper;
import com.example.sneaker.framework.message.model.Message;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableCreator {

    private static final String SORT_DIRECTION_ASC = "asc";
    private static final String SORT_DIRECTION_DESC = "desc";

    public static Pageable createPageable(PaginationRequest paginationRequest) {

        if (paginationRequest.getPage() != null && paginationRequest.getLimit() == null) {
            throw new BadRequestException(MessageHelper.getMessage(Message.Keys.E0040, "Paging request"));
        } else if (paginationRequest.getPage() == null && paginationRequest.getLimit() != null) {
            throw new BadRequestException(MessageHelper.getMessage(Message.Keys.E0040, "Paging request"));
        }

        Sort sort = paginationRequest.getSortBy() == null ? null : Sort.by(paginationRequest.getSortBy());

        if (sort != null) {
            if (paginationRequest.getSortDimension() == null || paginationRequest.getSortDimension().equalsIgnoreCase(SORT_DIRECTION_ASC)) {
                sort = sort.ascending();
            } else {
                sort = sort.descending();
            }
        }

        Pageable pageable = (paginationRequest.getPage() != null && paginationRequest.getLimit() != null) ?
                (sort != null ? PageRequest.of(paginationRequest.getPage() - 1, paginationRequest.getLimit(), sort)
                        : PageRequest.of(paginationRequest.getPage() - 1, paginationRequest.getLimit()))
                : (sort != null ? PageRequest.of(0, Integer.MAX_VALUE, sort) : PageRequest.of(0, Integer.MAX_VALUE));

        return pageable;
    }
}
