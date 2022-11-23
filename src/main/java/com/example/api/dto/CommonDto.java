package com.example.api.dto;


import java.util.function.Supplier;

public class CommonDto {
    private static final String DEFAULT_SUCCESS_CODE = "Y";

    public static final Supplier<Response> successResponse = Response::new;

    private static class Response {
        private final String isSuccess = DEFAULT_SUCCESS_CODE;
    }

}
