package com.example.sneaker.framework.exception;

import com.example.sneaker.framework.base.BaseException;
import com.example.sneaker.framework.message.model.Message;

import javax.print.attribute.standard.Severity;

public class ResourceNotFoundException extends BaseException {
    public ResourceNotFoundException(Message message) {
        super(message);
    }

    public ResourceNotFoundException(Message message, Severity severity) {
        super(message, severity);
    }

    public ResourceNotFoundException(Message message, Severity severity, Throwable throwable) {
        super(message, severity, throwable);
    }
}
