package com.example.sneaker.mapper.tokenManager;

import com.example.sneaker.model.entity.TokenManager;
import com.example.sneaker.model.response.authenticate.LoginResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-02T02:09:34+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Private Build)"
)
@Component
public class TokenManagerMapperImpl implements TokenManagerMapper {

    @Override
    public TokenManager toEntity(LoginResponse entity) {
        if ( entity == null ) {
            return null;
        }

        TokenManager tokenManager = new TokenManager();

        tokenManager.setAccessToken( entity.getAccessToken() );
        tokenManager.setRefreshToken( entity.getRefreshToken() );

        return tokenManager;
    }
}
