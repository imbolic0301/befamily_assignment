package com.example.api.dto;


import lombok.Getter;

import java.util.function.Supplier;

public class CommonDto {
    private static final String DEFAULT_SUCCESS_CODE = "Y";

    public static final Supplier<Response> successResponse = Response::new;

    @Getter
    public static class Response {
        private final String isSuccess = DEFAULT_SUCCESS_CODE;
    }

    @Getter
    public static class JwtResponse {
        public JwtResponse(String jwt) {
            this.jwt = jwt;
        }

        private final String isSuccess = DEFAULT_SUCCESS_CODE;
        private final String jwt;
    }


}
