package com.example.sneaker.controller;

import com.example.sneaker.framework.enums.DirectionSortEnum;
import com.example.sneaker.framework.exception.BadRequestException;
import com.example.sneaker.framework.exception.ResourceNotFoundException;
import com.example.sneaker.framework.handle.model.ResponseData;
import com.example.sneaker.framework.message.MessageHelper;
import com.example.sneaker.framework.message.model.Message;
import com.example.sneaker.framework.support.ResponseSupport;
import com.example.sneaker.model.entity.User;
import com.example.sneaker.model.request.page.PaginationRequest;
import com.example.sneaker.model.request.user.PasswordChangeRequest;
import com.example.sneaker.model.request.user.PasswordResetRequest;
import com.example.sneaker.model.request.user.RegisterUserRequest;
import com.example.sneaker.model.request.user.UserChangeInforRequest;
import com.example.sneaker.service.mail.IMailService;
import com.example.sneaker.service.order.IOrderService;
import com.example.sneaker.service.user.IUserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RequestMapping("/api/v1/account")
@RestController
@CrossOrigin
public class AccountController {

    private final Logger log = LoggerFactory.getLogger(AccountController.class);

    private final ResponseSupport responseSupport;

    private final IUserService userService;

    private final IMailService mailService;

    private final IOrderService orderService;

    public AccountController(
            IUserService userService,
            IMailService mailService,
            ResponseSupport responseSupport, IOrderService orderService
    ) {
        this.userService = userService;
        this.mailService = mailService;
        this.responseSupport = responseSupport;
        this.orderService = orderService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        User user = userService.registerUser(registerUserRequest)
                .orElseThrow();
    }

    @PutMapping("/change-password")
    public ResponseEntity<ResponseData> changePassword(@Valid @RequestBody PasswordChangeRequest passwordChangeRequest)
    {
           return userService.changePassword(passwordChangeRequest);
    }

    @PostMapping("/reset-password/init")
    public ResponseEntity<ResponseData> resetPasswordInit(@RequestParam String email){
        Optional<User> user = userService.requestPasswordReset(email);
        if(!user.isPresent()) throw new BadRequestException(MessageHelper.getMessage(Message.Keys.E0032));
        else mailService.sendPasswordResetEmail(user.get());
        return responseSupport.success(MessageHelper.getMessage(Message.Keys.I0001));
    }

    @PutMapping("/reset-password/finish")
    public void resetPasswordFinish(@Valid @RequestBody PasswordResetRequest passwordResetRequest){
        Optional<User> user = userService.completePasswordReset(passwordResetRequest);
        user.orElseThrow(() -> new ResourceNotFoundException(MessageHelper.getMessage("No user was found for this reset key")));
    }

    @GetMapping("/details")
    public ResponseEntity<ResponseData> getDetailsAccount() {
        return responseSupport.success(MessageHelper.getMessage(Message.Keys.E0003),
                                        ResponseData.builder().data(userService.getDetails()).build());
    }

    @GetMapping
    public ResponseEntity<ResponseData> getAccounts(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "limit", required = false) Integer limit,
            @RequestParam(name = "sortBy", required = false) String sortBy,
            @RequestParam(name = "sortDimension", required = false) DirectionSortEnum direction
    ) {
        PaginationRequest paginationRequest = new PaginationRequest(page, limit, sortBy, (direction == null) ? null : direction.name());
        return responseSupport.success(MessageHelper.getMessage(Message.Keys.E0003),
                                        ResponseData.builder().data(userService.getAccounts(paginationRequest)).build());
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<ResponseData> getOrdersById(@PathVariable UUID id) {
        return responseSupport.success(MessageHelper.getMessage(Message.Keys.I0001),
                ResponseData.builder().data(orderService.getOrderByAccountId(id)).build());
    }

    @PutMapping
    public ResponseEntity<ResponseData> updateUser(@Valid @RequestBody UserChangeInforRequest userChangeInforRequest) {
        return responseSupport.success(MessageHelper.getMessage(Message.Keys.I0001),
                ResponseData.builder().data(userService.updateUser(userChangeInforRequest)).build());
    }
}
