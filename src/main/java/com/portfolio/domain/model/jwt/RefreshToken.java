package com.portfolio.domain.model.jwt;

import lombok.Builder;
import lombok.Data;

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
