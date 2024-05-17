package com.example.sneaker.framework.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class SecurityUtils {

    public static Optional<String> getCurrentEmail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Optional.of(extractEmail(authentication));
    }

    public static String extractEmail(Authentication authentication){
        if(authentication == null) return null;
        else if(authentication.getPrincipal() instanceof UserDetails){
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        else if(authentication.getPrincipal() instanceof String){
            return  authentication.getName();
        }
        return null;
    }
}
