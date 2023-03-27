package com.portfolio.domain.model.jwt;

import java.util.Optional;

// access & refresh token 을 구현하기 위해서 여기 블로그보고 따라했다
// https://bcp0109.tistory.com/301
public interface TokenService {
    void saveRefreshToken(String getClientId, RefreshToken refreshToken);
}
