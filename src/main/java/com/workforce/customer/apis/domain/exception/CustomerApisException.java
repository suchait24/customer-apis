package com.workforce.customer.apis.domain.exception;

import org.springframework.http.HttpStatus;

public class CustomerApisException extends RuntimeException{

    public CustomerApisException(String message, HttpStatus status) {
        super(message);
    }
}
