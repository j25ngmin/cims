package com.kakaopay.cims.core.security;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.secretkey}")
    private String secretkey;

    // 토큰 유효시간 1시간
    private final long tokenValidTime = 60 * 60 * 1000L;

    //private final CustomUserDetailService userDetailsService;
    private final UserDetailsService userDetailsService;

    // 객체 초기화, secretKey를 Base64로 인코딩한다.
    @PostConstruct
    protected void init() {
    	secretkey = Base64.getEncoder().encodeToString(secretkey.getBytes());
    }

    // JWT 토큰 생성
    public String createToken(String userPk, List<String> roles, List<String> auth) {
        Claims claims = Jwts.claims().setSubject(userPk); // JWT payload 에 저장되는 정보단위
        claims.put("roles", roles); // 정보는 key / value 쌍으로 저장된다.
        claims.put("customRoles", auth); // 정보는 key / value 쌍으로 저장된다.
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretkey)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        List roles = getBody(token, "roles");
        List customRoles = getBody(token, "customRoles");
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        AuthorizedUser authorizedUser = ((AuthorizedUserSecurityAdapter) authentication.getPrincipal()).getAuthorizedUser();
        authorizedUser.setAuthorities(roles);
        authorizedUser.setRoles(customRoles);
        return authentication;
    }

    // 토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody().getSubject();
    }

    // 토큰에서 권한 정보 추출
    public List getBody(String token, String kind) {
        return (List) Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody().get(kind);
    }

    // Request의 Header에서 token 값을 가져옵니다. "CIMS-AUTH-TOKEN" : "TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("CIMS-AUTH-TOKEN");
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretkey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

}

