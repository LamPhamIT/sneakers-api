package com.example.sneaker.mapper.authority;

import com.example.sneaker.model.entity.Authority;
import com.example.sneaker.model.response.authority.AuthorityResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-02T02:09:34+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Private Build)"
)
@Component
public class AuthorityMapperImpl implements AuthorityMapper {

    @Override
    public AuthorityResponse toDto(Authority authority) {
        if ( authority == null ) {
            return null;
        }

        AuthorityResponse authorityResponse = new AuthorityResponse();

        authorityResponse.setId( authority.getId() );
        authorityResponse.setName( authority.getName() );

        return authorityResponse;
    }
}
