package com.example.sneaker.framework.support;

import com.example.sneaker.framework.handle.model.FieldError;
import com.example.sneaker.framework.handle.model.ResponseData;
import com.example.sneaker.framework.message.MessageHelper;
import com.example.sneaker.framework.message.model.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Component
public class ResponseSupport {

    public ResponseEntity<ResponseData> success(){
        return response(HttpStatus.OK, MessageHelper.getMessage(Message.Keys.I0001), null, null);
    }

    public ResponseEntity<ResponseData> success(Message message){
        return response(HttpStatus.OK, message, null, null);
    }

    public ResponseEntity<ResponseData> success(ResponseData responseData){
        return response(HttpStatus.OK, MessageHelper.getMessage(Message.Keys.I0001), responseData, null);
    }

    public ResponseEntity<ResponseData> success(Message message, ResponseData responseData){
        return response(HttpStatus.OK, message, responseData, null);
    }

    public ResponseEntity<ResponseData> failed(){
        return response(HttpStatus.BAD_REQUEST, MessageHelper.getMessage(Message.Keys.I0002), null, null);
    }

    public ResponseEntity<ResponseData> failed(Message message) {
        return response(HttpStatus.BAD_REQUEST, message, null, null);
    }

    public ResponseEntity<ResponseData> failed(HttpStatus httpStatus, Message message){
        return response(httpStatus, message, null, null);
    }

    public ResponseEntity<ResponseData> failed(HttpStatus httpStatus, Message message, List<FieldError> errors) {
        return response(httpStatus, message, null, errors);
    }

    public ResponseEntity<ResponseData> response(HttpStatus status, Message message, ResponseData data, List<FieldError> errors){
        if(data == null) data = new ResponseData();
        if(status.equals(HttpStatus.OK)) data.setIsSuccess(true);
        else {
            data.setIsSuccess(false);
            if(!CollectionUtils.isEmpty(errors)){
                data.setErrors(errors);
            }
            data.setData(null);
        }

        if(!Objects.isNull(message)){
            data.setMessage(message.getContent());
        }

        return ResponseEntity.status(status).body(data);
    }
}
