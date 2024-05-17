package com.example.sneaker.framework.config;

import com.example.sneaker.security.jwt.JWTFilter;
import com.example.sneaker.security.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final TokenProvider tokenProvider;

    public SecurityConfiguration(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    // Specify the actual origin of your frontend application
                    config.setAllowedOrigins(Collections.singletonList("http://localhost:5173"));
                    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
                    config.setExposedHeaders(Collections.singletonList("Authorization"));
                    config.setMaxAge(3600L);
                    return config;
                }))
                .authorizeRequests()
                .requestMatchers(
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/api/v1/account/register",
                        "/api/v1/auth/login",
                        "/api/v1/auth/refresh-token",
                        "/api/v1/account/reset-password/init",
                        "/api/v1/account/reset-password/finish",
                        "/api/v1/account/{id}/orders",
                        "/api/v1/payment/**"
                ).permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/product").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/v1/product").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "api/v1/product").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/v1/account").hasRole("ADMIN")
                .requestMatchers(
                        HttpMethod.GET,
                        "/api/v1/product",
                        "/api/v1/product/**",
                        "/api/v1/product/category/**",
                        "/api/v1/product/branch/**",
                        "/api/v1/cart",
                        "/api/v1/branches",
                        "/api/v1/order/{id}",
                        "/api/v1/categories",
                        "/api/v1/order"
                ).permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/order").permitAll()
                .requestMatchers(HttpMethod.GET, "api/v1/account/details").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/v1/cart").authenticated()
                .requestMatchers(HttpMethod.PATCH, "/api/v1/cart").authenticated()
                .requestMatchers(HttpMethod.DELETE, "api/v1/cart").authenticated()
                .and()
                .addFilterBefore(new JWTFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


}
