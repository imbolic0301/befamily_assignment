package com.example.interceptor;

import com.example.exception.GlobalException;
import com.example.service.MemberService;
import com.example.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        String jws = jwtTokenProvider.resolveToken(request);
        if (jws != null && jwtTokenProvider.validateToken(jws)) {
            String accessKey = jwtTokenProvider.accessKeyFrom(jws);
            return memberService.isValidAccessKey(accessKey);
        }
        throw new GlobalException("not valid session", HttpStatus.UNAUTHORIZED);
    }

}