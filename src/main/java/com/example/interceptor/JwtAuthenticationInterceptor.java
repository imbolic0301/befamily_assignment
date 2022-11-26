package com.example.interceptor;

import com.example.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        String token = jwtTokenProvider.resolveToken(request);
        System.out.println(request.getRequestURI());
        if (token != null && jwtTokenProvider.validateToken(token)) {
            System.out.println("do Filter called, and valid token");
        } else {
            System.out.println("do Filter called, but not valid token");
        }
        return true;
    }

}