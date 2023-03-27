package com.portfolio.domain.impl;

import com.portfolio.config.properties.AppProperties;
import com.portfolio.domain.model.jwt.RefreshToken;
import com.portfolio.domain.model.jwt.TokenProvider;
import com.portfolio.domain.model.jwt.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Transactional
public class TokenServiceImpl implements TokenService {
    private final RedisTemplate<String, String> redisTemplate;
    private final AppProperties appProperties;  // 7일
    private final String DATABASE = "REFRESH_TOKEN";

    @Override
    public void saveRefreshToken(String clientId, RefreshToken refreshToken) {
        String userId = refreshToken.getUserId();
        saveRefreshKeyByClientId(clientId, userId);
        saveRefreshTokenValue(clientId, userId, refreshToken.getRefreshValue());
    }

    private void saveRefreshKeyByClientId(String clientId, String userId) {
        SetOperations<String, String> keys = redisTemplate.opsForSet();
        StringJoiner joiner = new StringJoiner(":");
        joiner.add(DATABASE);
        joiner.add(userId);
        keys.add(joiner.toString(), clientId);
    }
    private void saveRefreshTokenValue(String clientId, String userId, String refreshValue) {
        ValueOperations<String, String> clientIdToken = redisTemplate.opsForValue();
        StringJoiner joiner = new StringJoiner(":");
        joiner.add(DATABASE);
        joiner.add(userId);
        joiner.add(clientId);
        clientIdToken.set(joiner.toString(),refreshValue,appProperties.getAuth().getRefreshTokenExpiryDay(), TimeUnit.DAYS);// 데브 서버 전용 (토큰 테스트)
    }
}
