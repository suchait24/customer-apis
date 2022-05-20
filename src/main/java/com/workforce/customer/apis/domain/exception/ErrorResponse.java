package com.workforce.customer.apis.domain.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@RequiredArgsConstructor
public class ErrorResponse {

    private String message;
    private HttpStatus status;
    private List<Error> errorList;

    public ErrorResponse(String message, HttpStatus status, List<Error> errorList) {
        this.message = message;
        this.status = status;
        this.errorList = List.copyOf(errorList);
    }
}
