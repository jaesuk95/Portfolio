package com.portfolio.domain.model.jwt;

import java.util.Optional;

public interface TokenService {
    void saveRefreshToken(String getClientId, RefreshToken refreshToken);
}
