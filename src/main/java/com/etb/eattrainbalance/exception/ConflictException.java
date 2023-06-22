package com.etb.eattrainbalance.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends BaseHttpException {

    public ConflictException() {
        super(HttpStatus.CONFLICT, "Depended Objects Exists");
    }

    public ConflictException(String s) {
        super(HttpStatus.CONFLICT, s);
    }
}
