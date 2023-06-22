package com.etb.eattrainbalance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AccessDeniedException extends BaseHttpException {
    private String message;

    public AccessDeniedException() {
        super(HttpStatus.FORBIDDEN, "");
        message = "Forbidden";
    }

    public AccessDeniedException(String s) {
        super(HttpStatus.FORBIDDEN, s);
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
