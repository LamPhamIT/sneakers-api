package com.example.sneaker.mapper.size;

import com.example.sneaker.model.entity.Size;
import com.example.sneaker.model.response.size.SizeResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-02T02:09:33+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Private Build)"
)
@Component
public class SizeMapperImpl implements SizeMapper {

    @Override
    public SizeResponse toDto(Size entity) {
        if ( entity == null ) {
            return null;
        }

        SizeResponse sizeResponse = new SizeResponse();

        sizeResponse.setSize( entity.getSize() );
        sizeResponse.setQuantity( entity.getQuantity() );

        return sizeResponse;
    }
}
