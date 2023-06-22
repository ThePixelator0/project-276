package com.etb.eattrainbalance.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseHttpException {
    private String message;

    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST, "");
        message = "Bad Request";
    }

    public BadRequestException(String s) {
        super(HttpStatus.BAD_REQUEST, s);
        message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
