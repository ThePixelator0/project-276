package com.etb.eattrainbalance.exception;

import org.springframework.http.HttpStatus;

public class BaseHttpException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;

    public BaseHttpException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
