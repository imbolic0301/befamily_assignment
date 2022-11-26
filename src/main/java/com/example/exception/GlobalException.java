package com.example.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class GlobalException extends Exception {

    private String message;
    private HttpStatus status;

    public GlobalException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
