package com.portfolio.domain.common;

import com.portfolio.domain.model.user.UserId;
import com.portfolio.utils.IpAddress;

public class UserCommand implements TriggeredBy{

    private UserId userId;
    private IpAddress ipAddress;
    private String clientId;
    private boolean admin;

    public void triggeredBy(UserId userId, IpAddress ipAddress, String clientId) {
        this.userId = userId;
        this.ipAddress = ipAddress;
        this.clientId = clientId;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getClientId() {
        return clientId;
    }

    @Override
    public UserId getUserId() {
        return userId;
    }

    @Override
    public IpAddress getIpAddress() {
        return ipAddress;
    }
}
