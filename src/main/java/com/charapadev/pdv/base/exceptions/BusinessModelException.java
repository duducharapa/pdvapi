package com.charapadev.pdv.base.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
public class BusinessModelException extends RuntimeException {

    public BusinessModelException(String message) {
        super(message);
    }

}
