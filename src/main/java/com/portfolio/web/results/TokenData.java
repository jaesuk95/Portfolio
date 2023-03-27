package com.portfolio.web.results;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenData {
    private String grantType;
    private String accessToken;
    private String role;
    private String refreshToken;
    private int accessTokenExpiresMin;
}
