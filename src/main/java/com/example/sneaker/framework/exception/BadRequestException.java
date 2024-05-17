package com.example.sneaker.framework.exception;

import com.example.sneaker.framework.base.BaseException;
import com.example.sneaker.framework.message.model.Message;

import javax.print.attribute.standard.Severity;

public class BadRequestException extends BaseException {
    
    public BadRequestException(Message message){
        super(message);
    }

    public BadRequestException(Message message, Severity severity){
        super(message, severity);
    }

    public BadRequestException(Message message, Severity severity, Throwable throwable){
        super(message, severity, throwable);
    }
}
