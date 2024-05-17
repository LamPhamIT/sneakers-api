package com.example.sneaker.framework.exception;

import com.example.sneaker.framework.base.BaseException;
import com.example.sneaker.framework.message.model.Message;

import javax.print.attribute.standard.Severity;

public class TokenRefreshException extends BaseException {

    private final static Long serialVersionUID = 1L;

    public TokenRefreshException(Message message){
        super(message);
    }

    public TokenRefreshException(final Message message, final Throwable rootCause) {
        super(message, Severity.ERROR, rootCause);
    }

}