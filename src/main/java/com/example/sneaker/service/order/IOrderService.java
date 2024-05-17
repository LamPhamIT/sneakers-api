package com.example.sneaker.service.order;

import com.example.sneaker.model.request.order.OrderChangeInforRequest;
import com.example.sneaker.model.request.order.OrderRequest;
import com.example.sneaker.model.request.page.PaginationRequest;
import com.example.sneaker.model.response.order.OrderResponse;
import com.example.sneaker.model.response.page.PageResponse;

import java.util.List;
import java.util.UUID;

public interface IOrderService {

    OrderResponse saveOrder(OrderRequest orderRequest);

    List<OrderResponse> getOrderByAccountId(UUID id);

    OrderResponse getOrderById(UUID id);

    OrderResponse updateOrderInfor(OrderChangeInforRequest orderChangeInforRequest);

    PageResponse getAllOrder(PaginationRequest paginationRequest);
}
