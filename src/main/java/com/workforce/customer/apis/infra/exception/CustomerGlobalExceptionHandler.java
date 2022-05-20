package com.workforce.customer.apis.infra.exception;

import com.workforce.customer.apis.domain.exception.Error;
import com.workforce.customer.apis.domain.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class CustomerGlobalExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<Error> errorList = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
           String message = error.getDefaultMessage();
           String fieldName = error.getObjectName();
           errorList.add(new Error(fieldName, message));
        });

        return buildErrorMessage(BAD_REQUEST, ex.getMessage(), errorList);
    }

    private ErrorResponse buildErrorMessage(HttpStatus status, String message, List<Error> errorList) {
        return new ErrorResponse(message, status, errorList);
    }
}
