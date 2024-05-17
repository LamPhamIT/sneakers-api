package com.example.sneaker.service.user;

import com.example.sneaker.framework.enums.AuthorityEnum;
import com.example.sneaker.framework.exception.BadRequestException;
import com.example.sneaker.framework.exception.ResourceNotFoundException;
import com.example.sneaker.framework.handle.model.ResponseData;
import com.example.sneaker.framework.message.MessageHelper;
import com.example.sneaker.framework.message.model.Message;
import com.example.sneaker.framework.support.ResponseSupport;
import com.example.sneaker.framework.utils.SecurityUtils;
import com.example.sneaker.framework.utils.StringUtils;
import com.example.sneaker.mapper.user.UserMapper;
import com.example.sneaker.model.entity.Authority;
import com.example.sneaker.model.entity.User;
import com.example.sneaker.model.request.page.PageableCreator;
import com.example.sneaker.model.request.page.PaginationRequest;
import com.example.sneaker.model.request.user.PasswordChangeRequest;
import com.example.sneaker.model.request.user.PasswordResetRequest;
import com.example.sneaker.model.request.user.RegisterUserRequest;
import com.example.sneaker.model.request.user.UserChangeInforRequest;
import com.example.sneaker.model.response.user.UserResponse;
import com.example.sneaker.repository.AuthorityRepository;
import com.example.sneaker.repository.UserRepository;
import org.mapstruct.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final ResponseSupport responseSupport;

    public UserService(
            UserRepository userRepository,
            AuthorityRepository authorityRepository,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder,
            ResponseSupport responseSupport
    ) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.responseSupport = responseSupport;
    }

    @Override
    @Transactional
    public Optional<User> registerUser(RegisterUserRequest registerUserRequest) {
        userRepository.findByEmail(registerUserRequest.getEmail())
                .ifPresent(user -> {
                    throw new BadRequestException(MessageHelper.getMessage(Message.Keys.E0013));
                });
        List<Authority> authorities = authorityRepository.findByName(AuthorityEnum.USER.getValue())
                .stream()
                .collect(Collectors.toList());
        String encodePassword = passwordEncoder.encode(registerUserRequest.getPassword());
        registerUserRequest.setPassword(encodePassword);
        User user = userMapper.toEntity(registerUserRequest);
        user.setAuthorities(authorities);
        return Optional.of(userRepository.save(user));
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseData> changePassword(PasswordChangeRequest passwordChangeRequest) {
        SecurityUtils
                .getCurrentEmail()
                .flatMap(userRepository::findByEmail)
                .ifPresent(user -> {
                    if (passwordEncoder.matches(passwordChangeRequest.getCurrentPassword(), user.getPassword())) {
                        user.setPassword(passwordEncoder.encode(passwordChangeRequest.getNewPassword()));
                        userRepository.save(user);
                    } else throw new BadRequestException(MessageHelper.getMessage(Message.Keys.E0035));
                });
        return responseSupport.success(MessageHelper.getMessage(Message.Keys.I0005, "Password"));
    }

    @Override
    public Optional<User> requestPasswordReset(String email) {
        return userRepository.findByEmail(email)
                .map(user -> {
                    user.setResetKey(StringUtils.generateKey(25));
                    return userRepository.save(user);
                });
    }

    @Override
    public Optional<User> completePasswordReset(PasswordResetRequest passwordResetRequest) {
        return userRepository.findByResetKey(passwordResetRequest.getResetKey())
                .map(user -> {
                    user.setResetKey(null);
                    user.setPassword(passwordEncoder.encode(passwordResetRequest.getNewPassword()));
                    return userRepository.save(user);
                });
    }

    @Override
    public UserResponse getDetails() {
        Optional<String> email = SecurityUtils.getCurrentEmail();
        Optional<User> user = userRepository.findByEmail(email.get());
        if (!user.isPresent()) {
            throw new ResourceNotFoundException(MessageHelper.getMessage(Message.Keys.E0003));
        }
        UserResponse userResponse = userMapper.toDto(user.get());
        return userResponse;
    }

    @Override
    public List<UserResponse> getAccounts(PaginationRequest paginationRequest) {
        Pageable pageable = PageableCreator.createPageable(paginationRequest);
        List<UserResponse> userResponses = userRepository.findAll(pageable).stream()
                .map(userMapper::toDto)
                .toList();
        return userResponses;
    }

    @Override
    @Transactional
    public UserResponse updateUser(UserChangeInforRequest userChangeInforRequest) {
        if(userChangeInforRequest == null || userChangeInforRequest.getId() == null) {
            throw new BadRequestException(MessageHelper.getMessage(Message.Keys.I0002));
        }
        Optional<User> user = userRepository.findById(userChangeInforRequest.getId());
        if (!user.isPresent()) {
            throw new ResourceNotFoundException(MessageHelper.getMessage(Message.Keys.E0023, "user", userChangeInforRequest.getId()));
        }
        if (userChangeInforRequest.getAuthorities() != null) {
            user.get().getAuthorities().clear();
            userChangeInforRequest.getAuthorities().stream()
                    .map(authority -> authorityRepository.findByName(authority.getName())
                            .orElseThrow(() -> new ResourceNotFoundException(MessageHelper.getMessage(Message.Keys.E0021, "role"))))
                    .forEach(role -> user.get().getAuthorities().add(role));
        }

        if (userChangeInforRequest.getIsDeleted() != null)
            user.get().setIsDeleted(userChangeInforRequest.getIsDeleted());
        if (userChangeInforRequest.getFullName() != null) user.get().setFullName(userChangeInforRequest.getFullName());
        User updatedUser = userRepository.save(user.get());
        return userMapper.toDto(updatedUser);
    }
}
