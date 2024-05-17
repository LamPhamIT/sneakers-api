package com.example.sneaker.controller;

import com.example.sneaker.framework.handle.model.ResponseData;
import com.example.sneaker.framework.support.ResponseSupport;
import com.example.sneaker.model.request.authenticate.LoginRequest;
import com.example.sneaker.model.request.authenticate.RefreshTokenRequest;
import com.example.sneaker.service.tokenManager.ITokenManagerService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/auth")
@RestController
@CrossOrigin
public class AuthenticateController {

    private final Logger log = LoggerFactory.getLogger(AuthenticateController.class);

    private final ResponseSupport responseSupport;

    private final ITokenManagerService tokenManagerService;

    public AuthenticateController(
            ResponseSupport responseSupport,
            ITokenManagerService tokenManagerService
    ) {
        this.responseSupport = responseSupport;
        this.tokenManagerService = tokenManagerService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseData> login(@Valid @RequestBody LoginRequest loginRequest) {
        return responseSupport.success(
                ResponseData.builder()
                        .data(tokenManagerService.login(loginRequest))
                        .build()
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseData> logout() {
        tokenManagerService.logout();
        return responseSupport.success();
    }

    @PutMapping("/refresh-token")
    public ResponseEntity<ResponseData> refreshToken(@RequestBody @Valid RefreshTokenRequest refreshTokenRequest){
        return responseSupport.success(
                new ResponseData()
                        .builder()
                        .data(tokenManagerService.refreshToken(refreshTokenRequest.getRefreshToken()))
                        .build()
        );
    }

}

