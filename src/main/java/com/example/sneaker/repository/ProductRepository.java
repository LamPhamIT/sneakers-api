package com.example.sneaker.repository;

import com.example.sneaker.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductBySlugAndIsDeletedIsFalse(String slug);

    Page<Product> findAllByCategoryIdAndIsDeletedIsFalse(Long categoryId, Pageable pageable);

    Page<Product> findAllByBranchIdAndIsDeletedIsFalse(Long branchId, Pageable pageable);

    @Query(value = "SELECT p FROM Product p WHERE p.name LIKE %:query% AND p.isDeleted != true")
    Page<Product> findAllByQuery(@Param("query") String query, Pageable pageable);

    Page<Product> findAllByIsDeletedIsFalse(Pageable pageable);


}
