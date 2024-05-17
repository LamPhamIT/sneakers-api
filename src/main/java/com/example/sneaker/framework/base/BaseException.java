package com.example.sneaker.framework.base;

import com.example.sneaker.framework.message.model.Message;
import lombok.Data;

import javax.print.attribute.standard.Severity;

@Data
public class BaseException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private Message msg;
    private Severity severity;
    private Throwable throwable;

    public BaseException(Message message){
        super(message.getContent());
        this.msg = message;
    }

    public BaseException(Message message, Severity severity) {
        super(message.getContent());
        this.msg = message;
        this.severity = severity;
    }

    public BaseException(Message message, Severity severity, Throwable throwable) {
        super(message.getContent(), throwable);
        this.msg = message;
        this.severity = severity;
        this.throwable = throwable;
    }

}
