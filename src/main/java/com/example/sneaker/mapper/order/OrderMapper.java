package com.example.sneaker.mapper.order;

import com.example.sneaker.model.entity.Order;
import com.example.sneaker.model.request.order.OrderRequest;
import com.example.sneaker.model.response.order.OrderResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponse toDto(Order entity);

    Order toEntity(OrderRequest orderRequest);

}
