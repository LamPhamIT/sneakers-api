package com.example.sneaker.security;

import com.example.sneaker.model.entity.User;
import com.example.sneaker.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserRepository userRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(user -> createSpringSecurityUser(user))
                .orElseThrow(() -> new UsernameNotFoundException("Email " + email + " was not found"));
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(User user){
        Collection<? extends GrantedAuthority> grantedAuthorities = user
                .getAuthorities()
                .stream()
                .map( authority -> new SimpleGrantedAuthority(authority.getName().toUpperCase() ))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}
