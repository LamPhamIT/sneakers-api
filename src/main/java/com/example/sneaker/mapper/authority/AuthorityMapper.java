package com.example.sneaker.mapper.authority;

import com.example.sneaker.model.entity.Authority;
import com.example.sneaker.model.response.authority.AuthorityResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {
    AuthorityResponse toDto(Authority authority);
}
