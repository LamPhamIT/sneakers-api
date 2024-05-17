package com.example.sneaker.framework.handle;

import com.example.sneaker.framework.exception.BadRequestException;
import com.example.sneaker.framework.exception.ResourceNotFoundException;
import com.example.sneaker.framework.exception.TokenRefreshException;
import com.example.sneaker.framework.handle.model.FieldError;
import com.example.sneaker.framework.handle.model.ResponseData;
import com.example.sneaker.framework.message.MessageHelper;
import com.example.sneaker.framework.message.model.Message;
import com.example.sneaker.framework.support.ResponseSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Component
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final ResponseSupport responseSupport;

    public GlobalExceptionHandler(ResponseSupport responseSupport) {
        this.responseSupport = responseSupport;
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ResponseData> handleBindException(BindException ex) {
        BindingResult result = ex.getBindingResult();

        List<FieldError> errors = result.getFieldErrors()
                .stream()
                .map(error -> new FieldError(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        if (errors.isEmpty()) {
            String msg = result.getAllErrors().get(0).getDefaultMessage();
            Message message = new Message();
            message.setContent(msg);
            return responseSupport.failed(HttpStatus.BAD_REQUEST, message);
        }

        return responseSupport.failed(HttpStatus.BAD_REQUEST,
                MessageHelper.getMessage(Message.Keys.E0011),
                errors
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseData> handleBadRequestException(BadRequestException ex) {
        return responseSupport.failed(HttpStatus.BAD_REQUEST, ex.getMsg());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResponseData> handleBadCredentialsException(BadCredentialsException ex) {
        return responseSupport.failed(HttpStatus.UNAUTHORIZED, MessageHelper.getMessage(Message.Keys.E0018));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseData> handleRuntimeException(RuntimeException ex) {
        return responseSupport.failed(HttpStatus.INTERNAL_SERVER_ERROR, MessageHelper.getMessage(ex.getMessage()));
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseData> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return responseSupport.failed(HttpStatus.NOT_FOUND, ex.getMsg());
    }

    @ExceptionHandler(TokenRefreshException.class)
    public ResponseEntity<ResponseData> handleTokenRefreshException(TokenRefreshException ex) {
        return responseSupport.failed(HttpStatus.UNAUTHORIZED, ex.getMsg());
    }

}
