package com.example.sneaker.mapper.order;

import com.example.sneaker.model.entity.Cart;
import com.example.sneaker.model.entity.Order;
import com.example.sneaker.model.request.cart.CartRequest;
import com.example.sneaker.model.request.order.OrderRequest;
import com.example.sneaker.model.response.order.OrderResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-02T02:09:34+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Private Build)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderResponse toDto(Order entity) {
        if ( entity == null ) {
            return null;
        }

        OrderResponse orderResponse = new OrderResponse();

        orderResponse.setId( entity.getId() );
        orderResponse.setTotal( entity.getTotal() );
        orderResponse.setIsPayed( entity.getIsPayed() );
        orderResponse.setStatus( entity.getStatus() );
        orderResponse.setPhoneNumber( entity.getPhoneNumber() );
        orderResponse.setAddress( entity.getAddress() );
        orderResponse.setFullName( entity.getFullName() );
        orderResponse.setEmail( entity.getEmail() );

        return orderResponse;
    }

    @Override
    public Order toEntity(OrderRequest orderRequest) {
        if ( orderRequest == null ) {
            return null;
        }

        Order order = new Order();

        order.setTotal( orderRequest.getTotal() );
        order.setIsPayed( orderRequest.getIsPayed() );
        order.setStatus( orderRequest.getStatus() );
        order.setPhoneNumber( orderRequest.getPhoneNumber() );
        order.setAddress( orderRequest.getAddress() );
        order.setFullName( orderRequest.getFullName() );
        order.setEmail( orderRequest.getEmail() );
        order.setCarts( cartRequestListToCartList( orderRequest.getCarts() ) );

        return order;
    }

    protected Cart cartRequestToCart(CartRequest cartRequest) {
        if ( cartRequest == null ) {
            return null;
        }

        Cart.CartBuilder cart = Cart.builder();

        cart.id( cartRequest.getId() );
        cart.quantity( cartRequest.getQuantity() );
        cart.size( cartRequest.getSize() );

        return cart.build();
    }

    protected List<Cart> cartRequestListToCartList(List<CartRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<Cart> list1 = new ArrayList<Cart>( list.size() );
        for ( CartRequest cartRequest : list ) {
            list1.add( cartRequestToCart( cartRequest ) );
        }

        return list1;
    }
}
