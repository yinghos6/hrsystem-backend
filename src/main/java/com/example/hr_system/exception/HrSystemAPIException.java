package com.example.hr_system.exception;

import org.springframework.http.HttpStatus;

public class HrSystemAPIException extends RuntimeException{

    private HttpStatus httpStatus;

    private String errorMessage;

    public HrSystemAPIException(HttpStatus httpStatus, String errorMessage) {
        super();
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
