package com.example.sneaker.repository;

import com.example.sneaker.model.entity.TokenManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenManagerRepository extends JpaRepository<TokenManager, UUID> {

    Optional<TokenManager> findByEmail(String email);

    Optional<TokenManager> findByRefreshToken(String refreshToken);

}
