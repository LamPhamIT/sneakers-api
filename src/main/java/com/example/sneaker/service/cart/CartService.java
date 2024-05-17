package com.example.sneaker.service.cart;

import com.example.sneaker.framework.exception.BadRequestException;
import com.example.sneaker.framework.message.MessageHelper;
import com.example.sneaker.framework.message.model.Message;
import com.example.sneaker.framework.utils.SecurityUtils;
import com.example.sneaker.mapper.cart.CartMapper;
import com.example.sneaker.model.entity.Cart;
import com.example.sneaker.model.entity.Product;
import com.example.sneaker.model.entity.User;
import com.example.sneaker.model.request.cart.CartRequest;
import com.example.sneaker.model.response.cart.CartResponse;
import com.example.sneaker.repository.CartRepository;
import com.example.sneaker.repository.ProductRepository;
import com.example.sneaker.repository.UserRepository;
import com.example.sneaker.service.product.ProductService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {
    private final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    @Autowired
    public CartService(CartRepository cartRepository, CartMapper cartMapper, UserRepository userRepository,
                       ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }


    @Override
    @Transactional
    public Optional<CartResponse> saveCart(CartRequest cartRequest) {
        Optional<Product> product = productRepository.findProductBySlugAndIsDeletedIsFalse(cartRequest.getProductSlug());
        if (!product.isPresent()) {
            throw new BadRequestException(MessageHelper.getMessage(Message.Keys.E0002));
        }
        Cart cart = cartMapper.toEntity(cartRequest);
        cart.setProduct(product.get());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        if (!user.isPresent()) {
            throw new BadRequestException(MessageHelper.getMessage(Message.Keys.E0002));
        }
        cart.setUser(user.get());
        cart.setIsDeleted(false);
        cart = cartRepository.save(cart);
        return Optional.of(cartMapper.toDto(cart));
    }

    @Override
    public List<CartResponse> getCart() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<User> user = userRepository.findByEmail(authentication.getName());
        if (user.isEmpty()) {
            throw new BadRequestException(MessageHelper.getMessage(Message.Keys.E0002));
        }
        List<CartResponse> result = cartRepository.findCartByUserIdAndIsDeletedFalse(user.get().getId())
                .stream()
                .map(cartMapper::toDto)
                .toList();
        return result;
    }

    @Override
    public void updateCart(CartRequest cartRequest) {
        Optional<Product> product = productRepository.findProductBySlugAndIsDeletedIsFalse(cartRequest.getProductSlug());
        if (!product.isPresent()) {
            throw new BadRequestException(MessageHelper.getMessage(Message.Keys.E0002));
        }
        Cart cart = cartMapper.toEntity(cartRequest);
        cart.setProduct(product.get());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        if (!user.isPresent()) {
            throw new BadRequestException(MessageHelper.getMessage(Message.Keys.E0002));
        }
        cart.setUser(user.get());
        cartRepository.save(cart);
    }

    @Override
    public void deleteCart(Long id) {
        Optional<String> email = SecurityUtils.getCurrentEmail();
        if(!email.isPresent()) {
            throw new BadRequestException(MessageHelper.getMessage(Message.Keys.I0002));
        }
        Optional<User> user = userRepository.findByEmail(email.get());
        if(!user.isPresent()) {
            throw new BadRequestException(MessageHelper.getMessage(Message.Keys.I0002));
        }
        Optional<Cart> cart = cartRepository.findCartById(id);
        if(!cart.isPresent()) {
            throw new BadRequestException(MessageHelper.getMessage(Message.Keys.E0023, "Cart", id));
        }
        if(user.get().getId() != cart.get().getUser().getId()) {
            throw new BadRequestException(MessageHelper.getMessage(Message.Keys.I0002));
        }
        cart.get().setIsDeleted(true);
        cartRepository.save(cart.get());
    }
}
