package com.example.sneaker.controller;

import com.example.sneaker.framework.enums.DirectionSortEnum;
import com.example.sneaker.framework.handle.model.ResponseData;
import com.example.sneaker.framework.support.ResponseSupport;
import com.example.sneaker.model.request.order.OrderChangeInforRequest;
import com.example.sneaker.model.request.order.OrderRequest;
import com.example.sneaker.model.request.page.PaginationRequest;
import com.example.sneaker.service.order.IOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    private final IOrderService orderService;

    private final ResponseSupport responseSupport;

    public OrderController(IOrderService orderService, ResponseSupport responseSupport) {
        this.orderService = orderService;
        this.responseSupport = responseSupport;
    }

    @PostMapping
    public ResponseEntity<ResponseData> saveOrder(@RequestBody OrderRequest orderRequest) {
        return responseSupport.success(ResponseData.builder().data(orderService.saveOrder(orderRequest)).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getOrderById(@PathVariable UUID id) {
        return responseSupport.success(ResponseData.builder().data(orderService.getOrderById(id)).build());
    }

    @PutMapping
    public ResponseEntity<ResponseData> updateOrder(@RequestBody OrderChangeInforRequest orderChangeInforRequest) {
        return responseSupport.success(ResponseData.builder().data(orderService.updateOrderInfor(orderChangeInforRequest)).build());
    }

    @GetMapping
    public ResponseEntity<ResponseData> getALlOrders(@RequestParam(name = "query", required = false) String query,
                                                     @RequestParam(name = "page", required = false) Integer page,
                                                     @RequestParam(name = "limit", required = false) Integer limit,
                                                     @RequestParam(name = "sortBy", required = false) String sortBy,
                                                     @RequestParam(name = "sortDimension", required = false) DirectionSortEnum direction
    ) {
        PaginationRequest paginationRequest = new PaginationRequest(page, limit, sortBy, (direction == null) ? null : direction.name());
        return responseSupport.success(ResponseData.builder().data(orderService.getAllOrder(paginationRequest)).build());
    }
}
