package com.portfolio.domain.model.jwt;

import lombok.Builder;
import lombok.Data;

// access & refresh token 을 구현하기 위해서 여기 블로그보고 따라했다
// https://bcp0109.tistory.com/301
@Data
public class RefreshToken {
    private String clientId;
    private String userId;
    private String refreshValue;

    public String getUniqueKey(){
        return clientId+userId;
    }

    @Builder
    public RefreshToken(String userId, String value) {
        this.userId = userId;
        this.refreshValue = value;
    }
}
