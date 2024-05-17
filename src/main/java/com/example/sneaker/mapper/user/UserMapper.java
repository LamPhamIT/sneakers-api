package com.example.sneaker.mapper.user;

import com.example.sneaker.model.entity.User;
import com.example.sneaker.model.request.user.RegisterUserRequest;
import com.example.sneaker.model.response.user.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(RegisterUserRequest registerUserRequest);

    UserResponse toDto(User user);

}