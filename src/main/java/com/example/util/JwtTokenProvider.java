package com.example.util;

import com.example.constant.EnvConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private static final String SECRET_KEY_LITERAL = "7d5d44e2-6cbb-11ed-a1eb-0242ac120002";
    private String secretKey;

    // 객체 초기화, secretKey 를 Base64로 인코딩한다.
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(SECRET_KEY_LITERAL.getBytes());
    }

    // JWT 토큰 생성
    public String createToken(Map<String, Object> privateClaimMap) {
        Date now = new Date();
        return Jwts.builder()
                .setClaims(privateClaimMap) // 프라이빗 클레임 추가
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + EnvConstants.SESSION_LIVE_MILLISECONDS)) // 만료시간 설정(밀리초)
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 알고화 알고리즘과 시크릿 키 설정
                .compact();
    }

    // 요청에서 정해진 헤더의 파라미터를 가져온다.
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(EnvConstants.AUTH_HEADER_NAME);
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

    public String accessKeyFrom(String jws) throws Exception {
        return parseJws(jws).get(EnvConstants.SESSION_KEY_NAME).toString();
    }

    public Map<String, Object> parseJws(String jws) throws Exception {
        System.out.println("JWS : ");
        System.out.println(jws);
        Map<String, Object> claimMap;
        try {
            claimMap = Jwts.parser()
                    .setSigningKey(secretKey) // Set Key
                    .parseClaimsJws(jws) // 파싱 및 검증, 실패 시 에러
                    .getBody();
        } catch (ExpiredJwtException e) { // 토큰이 만료되었을 경우
            throw new Exception("expired token");
        } catch (Exception e) { // 그외 에러났을 경우
            throw new Exception("not valid jws");
        }
        return claimMap;
    }

}
