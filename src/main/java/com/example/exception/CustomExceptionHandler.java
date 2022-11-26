package com.example.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    protected Object handleGlobalException(GlobalException e, HttpServletRequest request) {
        ExceptionDto.CommonResponse responseBody = new ExceptionDto.CommonResponse(e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(responseBody);
    }

}
