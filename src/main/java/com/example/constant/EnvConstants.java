package com.example.constant;

public class EnvConstants {
    // 세션 만료 시간
    public final static Long SESSION_LIVE_SECONDS = 300L;
    public final static Long SESSION_LIVE_MILLISECONDS = 300L * 1000;
    // 세션 헤더 토큰 이름
    public static final String AUTH_HEADER_NAME = "Authorization";
    // 세션 헤더 토큰 이름
    public static final String SESSION_KEY_NAME = "accessKey";
}
