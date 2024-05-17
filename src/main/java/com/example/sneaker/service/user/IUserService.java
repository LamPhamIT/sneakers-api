package com.example.sneaker.service.user;

import com.example.sneaker.framework.handle.model.ResponseData;
import com.example.sneaker.model.entity.User;
import com.example.sneaker.model.request.page.PaginationRequest;
import com.example.sneaker.model.request.user.PasswordChangeRequest;
import com.example.sneaker.model.request.user.PasswordResetRequest;
import com.example.sneaker.model.request.user.RegisterUserRequest;
import com.example.sneaker.model.request.user.UserChangeInforRequest;
import com.example.sneaker.model.response.user.UserResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    Optional<User> registerUser(RegisterUserRequest registerUserRequest);

    ResponseEntity<ResponseData> changePassword(PasswordChangeRequest passwordChangeRequest);

    Optional<User> requestPasswordReset(String email);

    Optional<User>  completePasswordReset(PasswordResetRequest passwordResetRequest);

    UserResponse getDetails();

    List<UserResponse> getAccounts(PaginationRequest paginationRequest);

    UserResponse updateUser(UserChangeInforRequest userChangeInforRequest);
}
