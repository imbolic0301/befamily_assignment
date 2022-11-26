package com.example.exception;

import lombok.Getter;
import lombok.ToString;

public class ExceptionDto {
    //ExceptionHandler에서 Exception 발생시 리턴할 기본 형태
    @ToString
    @Getter
    public static class CommonResponse {
        private String message;

        public CommonResponse(String message) {
            if(message.indexOf("[") > -1) {
                this.message = message.substring(0, message.indexOf("["));
            } else {
                this.message = message;
            }
        }
    }
}
