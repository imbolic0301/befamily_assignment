package com.example.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    // 세션체크에 쓰일 토큰 이름
    private static final String AUTH_HEADER_NAME = "Authorization";
    // 토큰 유효시간 30분
    private static final long TOKEN_ALIVE_SECONDS = 5 * 60 * 1000L;

    private static final String SECRET_KEY_LITERAL = "7d5d44e2-6cbb-11ed-a1eb-0242ac120002";
    private String secretKey;

    // JWT 토큰에서 사용할
    private static final String JWT_CLAIM_MAP_NAME = "roles";

    // 객체 초기화, secretKey 를 Base64로 인코딩한다.
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(SECRET_KEY_LITERAL.getBytes());
    }

    // JWT 토큰 생성
    public String createToken(String userId, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userId); // JWT payload 에 저장되는 정보단위
        claims.put(JWT_CLAIM_MAP_NAME, roles); // 정보는 key / value 쌍으로 저장된다.
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + TOKEN_ALIVE_SECONDS)) // 만료시간 설정(밀리초)
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 알고화 알고리즘과 시크릿 키 설정
                .compact();
    }

    // 요청에서 정해진 헤더의 파라미터를 가져온다.
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(AUTH_HEADER_NAME);
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

}
