package com.example.sneaker.mapper.tokenManager;

import com.example.sneaker.model.entity.TokenManager;
import com.example.sneaker.model.response.authenticate.LoginResponse;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TokenManagerMapper {

    TokenManager toEntity(LoginResponse entity);

}
