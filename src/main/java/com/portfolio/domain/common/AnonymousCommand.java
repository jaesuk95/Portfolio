package com.portfolio.domain.common;

import com.portfolio.utils.IpAddress;

public class AnonymousCommand implements TriggeredFrom {
    private IpAddress ipAddress;
    private Long userId;
    private boolean admin;
    private String clientId;

    public void triggeredBy(IpAddress ipAddress, String clientId) {
        this.ipAddress = ipAddress;
        this.clientId = clientId;
    }
    @Override
    public IpAddress getIpAddress() {
        return ipAddress;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getClientId() {
        return clientId;
    }
}
