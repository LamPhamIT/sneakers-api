package com.example.sneaker.repository;

import com.example.sneaker.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findCartByUserIdAndIsDeletedFalse(UUID uuid);

    Optional<Cart> findCartById(Long id);
}
