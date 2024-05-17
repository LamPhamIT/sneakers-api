package com.example.sneaker.service.tokenManager;

import com.example.sneaker.framework.exception.NotFoundTokenManagerException;
import com.example.sneaker.framework.exception.TokenRefreshException;
import com.example.sneaker.framework.message.MessageHelper;
import com.example.sneaker.framework.message.model.Message;
import com.example.sneaker.framework.utils.SecurityUtils;
import com.example.sneaker.framework.utils.StringUtils;
import com.example.sneaker.mapper.tokenManager.TokenManagerMapper;
import com.example.sneaker.model.entity.TokenManager;
import com.example.sneaker.model.request.authenticate.LoginRequest;
import com.example.sneaker.model.response.authenticate.LoginResponse;
import com.example.sneaker.repository.TokenManagerRepository;
import com.example.sneaker.security.DomainUserDetailsService;
import com.example.sneaker.security.jwt.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class TokenManagerService implements ITokenManagerService {

    private final Logger log = LoggerFactory.getLogger(TokenManagerService.class);

    private final AuthenticationManager authenticationManager;

    private final TokenManagerMapper tokenManagerMapper;

    private final TokenManagerRepository tokenManagerRepository;

    private final TokenProvider tokenProvider;

    private final DomainUserDetailsService domainUserDetailsService;

    public TokenManagerService(
            AuthenticationManager authenticationManager,
            TokenManagerMapper tokenManagerMapper,
            TokenManagerRepository tokenManagerRepository,
            TokenProvider tokenProvider,
            DomainUserDetailsService domainUserDetailsService
    ) {
        this.authenticationManager = authenticationManager;
        this.tokenManagerMapper = tokenManagerMapper;
        this.tokenManagerRepository = tokenManagerRepository;
        this.tokenProvider = tokenProvider;
        this.domainUserDetailsService = domainUserDetailsService;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );

        authentication = authenticationManager.authenticate(authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = tokenProvider.createToken(authentication);
        String refreshToken = StringUtils.generateKey(25);

        LoginResponse loginReponse = LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        tokenManagerRepository.findByEmail(loginRequest.getEmail())
                .ifPresent(tokenManagerRepository::delete);

        TokenManager tokenManager = tokenManagerMapper.toEntity(loginReponse);
        tokenManager.setEmail(loginRequest.getEmail());
        tokenManager.setExpireTime(Instant.now().plus(Duration.ofDays(7)));

        tokenManagerRepository.save(tokenManager);

        return loginReponse;
    }

    @Override
    public void logout() {
        String email = SecurityUtils.getCurrentEmail()
                .orElse(null);

        TokenManager tokenManager = tokenManagerRepository.findByEmail(email)
                .orElse(null);

        if(tokenManager == null) throw new NotFoundTokenManagerException(MessageHelper.getMessage(Message.Keys.E0014, "TokenManager"));

        tokenManagerRepository.delete(tokenManager);
    }

    @Override
    public LoginResponse refreshToken(String refreshToken) {
        return tokenManagerRepository.findByRefreshToken(refreshToken)
                .map(this::verifyExpiration)
                .map(this::createRefreshToken)
                .orElseThrow(() -> new TokenRefreshException(MessageHelper.getMessage(Message.Keys.E0031)));
    }

    @Override
    public TokenManager verifyExpiration(TokenManager tokenManager) {
        if(tokenManager.getExpireTime().isAfter(Instant.now())){
            return tokenManager;
        }
        tokenManagerRepository.delete(tokenManager);
        throw new TokenRefreshException(MessageHelper.getMessage(Message.Keys.E0030));
    }

    @Override
    public LoginResponse createRefreshToken(TokenManager tokenManager) {
        UserDetails userDetails = domainUserDetailsService.loadUserByUsername(tokenManager.getEmail());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = tokenProvider.createToken(authentication);
        String refreshToken = StringUtils.generateKey(25);

        tokenManager.setRefreshToken(refreshToken);
        tokenManager.setAccessToken(accessToken);

        tokenManagerRepository.save(tokenManager);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
