package com.portfolio.domain.model.jwt;

import com.portfolio.config.properties.AppProperties;
import com.portfolio.domain.model.user.Role;
import com.portfolio.domain.model.user.SimpleUser;
import com.portfolio.web.results.TokenData;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDateTime;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

// 인프런 강의 (refresh & access 없음, 기본적인 토큰만)
// https://kyung-a.tistory.com/32

// access & refresh token 을 구현하기 위해서 여기 블로그보고 따라했다
// https://bcp0109.tistory.com/301

@Slf4j
@Component
public class TokenProvider {    // 토큰 유효성 검사
    private static final String AUTHORITIES_KEY = "auth";
    private static final String USER_ID = "userId";
    private static final String BEARER_TYPE = "Bearer";
    private final AppProperties appProperties;  // application.properties 에서 확인

    private Key key;

    public TokenProvider(AppProperties appProperties) {
        this.appProperties = appProperties;
        byte[] keyBytes = Decoders.BASE64.decode(appProperties.getAuth().getTokenSecret());
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenData generateTokenData(Authentication authentication) {
        // 권한들 가져오기
        List<Role> collect = authentication.getAuthorities().stream()
                .map(grantedAuthority -> Role.valueOf(grantedAuthority.getAuthority()))
                .collect(Collectors.toList());
        Role role = Role.getHighest(collect);

        LocalDateTime now = LocalDateTime.now();

        // Access Token 생성
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())       // payload "sub": "name"
                .claim(AUTHORITIES_KEY, role)        // payload "auth": "ROLE_USER"
                .claim(USER_ID, ((SimpleUser) authentication.getPrincipal()).getUserId().value())        // payload "auth": "ROLE_USER"
                .setExpiration(now.plusMinutes(appProperties.getAuth().getTokenExpiryMin()).toDate())        // payload "exp": 1516239022 (예시)
                .signWith(key, SignatureAlgorithm.HS512)    // header "alg": "HS512"
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(now.plusDays(appProperties.getAuth().getRefreshTokenExpiryDay()).toDate())
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return TokenData.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .role(role.name())
                .accessTokenExpiresMin(appProperties.getAuth().getTokenExpiryMin())
                .refreshToken(refreshToken)
                .build();
    }

    public Authentication getAuthentication(String accessToken) {
        // 토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());



        // UserDetails 객체를 만들어서 Authentication 리턴
        UserDetails principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    public Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }


}
