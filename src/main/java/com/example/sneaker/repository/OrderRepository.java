package com.example.sneaker.repository;

import com.example.sneaker.model.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findAllByUser_Id(UUID id);

    Optional<Order> findById(UUID id);

    Page<Order> findAllByIsDeletedIsFalse(Pageable pageable);
}
