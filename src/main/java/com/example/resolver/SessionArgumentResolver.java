package com.example.resolver;

import com.example.constant.EnvConstants;
import com.example.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class SessionArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String MEMBER_ID_PARAMETER = "id";
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        System.out.println("supportsParameter called");
        return parameter.getParameterName().equalsIgnoreCase(MEMBER_ID_PARAMETER)
                && parameter.getParameterType().equals(Long.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String jws = webRequest.getHeader(EnvConstants.AUTH_HEADER_NAME);
        Map<String, Object> claimMap = jwtTokenProvider.parseJws(jws);
        String idString = claimMap.get(MEMBER_ID_PARAMETER).toString();
        return Long.valueOf(idString);
    }

}