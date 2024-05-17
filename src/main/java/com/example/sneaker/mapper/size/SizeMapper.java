package com.example.sneaker.mapper.size;

import com.example.sneaker.model.entity.Size;
import com.example.sneaker.model.response.size.SizeResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SizeMapper{
    SizeResponse toDto(Size entity);
}
