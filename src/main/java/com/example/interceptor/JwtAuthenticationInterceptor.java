package com.example.interceptor;

import com.example.service.MemberService;
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
    private final MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        String jws = jwtTokenProvider.resolveToken(request);
        System.out.println("jws : "+ jws);
        System.out.println(request.getRequestURI());
        if (jws != null && jwtTokenProvider.validateToken(jws)) {
            String accessKey = jwtTokenProvider.accessKeyFrom(jws);
            return memberService.isValidAccessKey(accessKey);
        }
        throw new Exception("not valid session");
    }

}