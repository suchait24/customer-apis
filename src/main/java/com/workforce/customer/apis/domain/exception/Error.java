package com.workforce.customer.apis.domain.exception;

import lombok.Data;

@Data
public class Error {

    private String key;
    private String value;

    public Error(String fieldName, String message) {
        this.key = fieldName;
        this.value = message;
    }
}
