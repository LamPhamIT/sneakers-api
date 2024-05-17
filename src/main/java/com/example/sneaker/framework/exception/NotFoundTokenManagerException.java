package com.example.sneaker.framework.exception;

import com.example.sneaker.framework.base.BaseException;
import com.example.sneaker.framework.message.model.Message;

import javax.print.attribute.standard.Severity;

public class NotFoundTokenManagerException extends BaseException {

    public NotFoundTokenManagerException(Message message){
        super(message);
    }

    public NotFoundTokenManagerException(Message message, Severity severity){
        super(message, severity);
    }

    public NotFoundTokenManagerException(Message message, Severity severity, Throwable throwable){
        super(message, severity, throwable);
    }

}
