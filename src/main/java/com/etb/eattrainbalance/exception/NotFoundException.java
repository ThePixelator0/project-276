package com.etb.eattrainbalance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends BaseHttpException {

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public NotFoundException() {
        super(HttpStatus.NOT_FOUND, "");
        message = "Not Found Exception";
    }

    public NotFoundException(String s) {
        super(HttpStatus.NOT_FOUND, s);
        message = s;
    }
}
