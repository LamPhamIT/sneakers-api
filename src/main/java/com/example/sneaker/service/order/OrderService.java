package com.example.sneaker.service.order;

import com.example.sneaker.framework.exception.BadRequestException;
import com.example.sneaker.framework.exception.ResourceNotFoundException;
import com.example.sneaker.framework.message.MessageHelper;
import com.example.sneaker.framework.message.model.Message;
import com.example.sneaker.framework.utils.SecurityUtils;
import com.example.sneaker.mapper.cart.CartMapper;
import com.example.sneaker.mapper.order.OrderMapper;
import com.example.sneaker.model.entity.Cart;
import com.example.sneaker.model.entity.Order;
import com.example.sneaker.model.entity.Product;
import com.example.sneaker.model.entity.User;
import com.example.sneaker.model.request.cart.CartRequest;
import com.example.sneaker.model.request.order.OrderChangeInforRequest;
import com.example.sneaker.model.request.order.OrderRequest;
import com.example.sneaker.model.request.page.PageableCreator;
import com.example.sneaker.model.request.page.PaginationRequest;
import com.example.sneaker.model.response.order.OrderResponse;
import com.example.sneaker.model.response.page.PageResponse;
import com.example.sneaker.model.response.product.ProductResponse;
import com.example.sneaker.repository.*;
import com.example.sneaker.service.cart.ICartService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService implements IOrderService {

    private final OrderMapper orderMapper;

    private final UserRepository userRepository;

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    private final ICartService cartService;
    private final ProductRepository productRepository;

    private final CartMapper cartMapper;
    private final SizeRepository sizeRepository;

    public OrderService(OrderMapper orderMapper, UserRepository userRepository, OrderRepository orderRepository,
                        CartRepository cartRepository, ICartService cartService,
                        ProductRepository productRepository, CartMapper cartMapper,
                        SizeRepository sizeRepository) {
        this.orderMapper = orderMapper;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.cartService = cartService;
        this.productRepository = productRepository;
        this.cartMapper = cartMapper;
        this.sizeRepository = sizeRepository;
    }

    @Override
    @Transactional
    public OrderResponse saveOrder(OrderRequest orderRequest) {
        String email = SecurityUtils.getCurrentEmail()
                .orElse(null);
        Optional<User> user = userRepository.findByEmail(email);

        List<CartRequest> cartRequests = orderRequest.getCarts();
        Order order = orderMapper.toEntity(orderRequest);

        List<Cart> carts = new ArrayList<>();
        user.ifPresent(order::setUser);
        order.setIsDeleted(false);
        order = orderRepository.save(order);
        for (CartRequest c : cartRequests) {
            Optional<Product> product = productRepository.findProductBySlugAndIsDeletedIsFalse(c.getProductSlug());
            if (!product.isPresent()) {
                throw new BadRequestException(MessageHelper.getMessage(Message.Keys.E0002));
            }
            product.get().getSizes().stream()
                    .forEach(size -> {
                        if (size.getSize().equals(c.getSize()) && size.getQuantity() < c.getQuantity()) {
                            throw new BadRequestException(MessageHelper.getMessage(Message.Keys.I0002));
                        }
                        size.setQuantity(size.getQuantity() - c.getQuantity());
                        sizeRepository.save(size);
                    });
            Cart cart = cartMapper.toEntity(c);
            user.ifPresent(cart::setUser);
            cart.setOrder(order);
            cart.setProduct(product.get());
            cart.setIsDeleted(true);
            carts.add(cart);
        }
        carts = cartRepository.saveAll(carts);
        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderResponse> getOrderByAccountId(UUID id) {
        if (id == null) {
            throw new BadRequestException(MessageHelper.getMessage(Message.Keys.E0021, "id"));
        }
        List<OrderResponse> orderResponses = orderRepository.findAllByUser_Id(id).stream()
                .map(orderMapper::toDto)
                .toList();
        return orderResponses;
    }

    @Override
    public OrderResponse getOrderById(UUID id) {
        if (id == null) {
            throw new BadRequestException(MessageHelper.getMessage(Message.Keys.E0021, "id"));
        }
        Optional<Order> order = orderRepository.findById(id);
        if (!order.isPresent()) throw new ResourceNotFoundException(MessageHelper.getMessage(Message.Keys.E0014, "id"));
        return orderMapper.toDto(order.get());
    }

    @Override
    public OrderResponse updateOrderInfor(OrderChangeInforRequest orderChangeInforRequest) {
        Optional<Order> originalOrder = orderRepository.findById(orderChangeInforRequest.getId());
        if (!originalOrder.isPresent()) {
            throw new ResourceNotFoundException(MessageHelper.getMessage(Message.Keys.E0023, "order", orderChangeInforRequest.getId()));
        }
        if (orderChangeInforRequest.getIsPayed() != null)
            originalOrder.get().setIsPayed(orderChangeInforRequest.getIsPayed());
        if (orderChangeInforRequest.getStatus() != null)
            originalOrder.get().setStatus(orderChangeInforRequest.getStatus());
        if (orderChangeInforRequest.getPhoneNumber() != null)
            originalOrder.get().setPhoneNumber(orderChangeInforRequest.getPhoneNumber());
        if (orderChangeInforRequest.getAddress() != null)
            originalOrder.get().setAddress(orderChangeInforRequest.getAddress());
        if (orderChangeInforRequest.getFullName() != null)
            originalOrder.get().setFullName(orderChangeInforRequest.getFullName());
        if (orderChangeInforRequest.getEmail() != null)
            originalOrder.get().setEmail(orderChangeInforRequest.getEmail());
        OrderResponse orderResponse = orderMapper.toDto(orderRepository.save(originalOrder.get()));
        return orderResponse;
    }

    @Override
    public PageResponse getAllOrder(PaginationRequest paginationRequest) {
        Pageable pageable = PageableCreator.createPageable(paginationRequest);
        Page<Order> page = orderRepository.findAllByIsDeletedIsFalse(pageable);
        List<OrderResponse> responseData = page.getContent().stream().map(orderMapper::toDto).toList();

        return new PageResponse(
                page.getNumber() + 1,
                page.getNumberOfElements(),
                page.getTotalPages(),
                paginationRequest.getSortBy(),
                paginationRequest.getSortDimension(),
                responseData
        );
    }


}
