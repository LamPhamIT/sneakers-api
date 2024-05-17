package com.example.sneaker.service.tokenManager;

import com.example.sneaker.model.entity.TokenManager;
import com.example.sneaker.model.request.authenticate.LoginRequest;
import com.example.sneaker.model.response.authenticate.LoginResponse;

public interface ITokenManagerService {

    LoginResponse login(LoginRequest loginRequest);

    void logout();

    LoginResponse refreshToken(String refreshToken);

    TokenManager verifyExpiration(TokenManager tokenManager);

    LoginResponse createRefreshToken(TokenManager tokenManager);

}
