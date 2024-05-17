package com.example.sneaker.mapper.user;

import com.example.sneaker.model.entity.Authority;
import com.example.sneaker.model.entity.User;
import com.example.sneaker.model.request.user.RegisterUserRequest;
import com.example.sneaker.model.response.authority.AuthorityResponse;
import com.example.sneaker.model.response.user.UserResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-02T02:09:34+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Private Build)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(RegisterUserRequest registerUserRequest) {
        if ( registerUserRequest == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.email( registerUserRequest.getEmail() );
        user.password( registerUserRequest.getPassword() );
        user.avatar( registerUserRequest.getAvatar() );
        user.fullName( registerUserRequest.getFullName() );
        user.authorities( authorityResponseListToAuthorityList( registerUserRequest.getAuthorities() ) );

        return user.build();
    }

    @Override
    public UserResponse toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( user.getId() );
        userResponse.setEmail( user.getEmail() );
        userResponse.setFullName( user.getFullName() );
        userResponse.setAvatar( user.getAvatar() );
        userResponse.setCreateBy( user.getCreateBy() );
        userResponse.setCreatedDate( user.getCreatedDate() );
        userResponse.setLastModifiedBy( user.getLastModifiedBy() );
        userResponse.setLastModifiedDate( user.getLastModifiedDate() );
        userResponse.setAuthorities( authorityListToAuthorityResponseList( user.getAuthorities() ) );
        userResponse.setIsDeleted( user.getIsDeleted() );

        return userResponse;
    }

    protected Authority authorityResponseToAuthority(AuthorityResponse authorityResponse) {
        if ( authorityResponse == null ) {
            return null;
        }

        Authority.AuthorityBuilder authority = Authority.builder();

        authority.id( authorityResponse.getId() );
        authority.name( authorityResponse.getName() );

        return authority.build();
    }

    protected List<Authority> authorityResponseListToAuthorityList(List<AuthorityResponse> list) {
        if ( list == null ) {
            return null;
        }

        List<Authority> list1 = new ArrayList<Authority>( list.size() );
        for ( AuthorityResponse authorityResponse : list ) {
            list1.add( authorityResponseToAuthority( authorityResponse ) );
        }

        return list1;
    }

    protected AuthorityResponse authorityToAuthorityResponse(Authority authority) {
        if ( authority == null ) {
            return null;
        }

        AuthorityResponse authorityResponse = new AuthorityResponse();

        authorityResponse.setId( authority.getId() );
        authorityResponse.setName( authority.getName() );

        return authorityResponse;
    }

    protected List<AuthorityResponse> authorityListToAuthorityResponseList(List<Authority> list) {
        if ( list == null ) {
            return null;
        }

        List<AuthorityResponse> list1 = new ArrayList<AuthorityResponse>( list.size() );
        for ( Authority authority : list ) {
            list1.add( authorityToAuthorityResponse( authority ) );
        }

        return list1;
    }
}
