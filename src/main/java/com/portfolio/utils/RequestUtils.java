package com.portfolio.utils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

    private RequestUtils() {
    }

    public static IpAddress getIpAddress(HttpServletRequest request) {
        String remoteAddress = request.getRemoteAddr();
        String x;
        if ((x = request.getHeader("X-FORWARDED-FOR")) != null) {
            remoteAddress = x;
            int idx = remoteAddress.indexOf(',');
            if (idx > -1) {
                remoteAddress = remoteAddress.substring(0, idx);
            }
        }
        return new IpAddress(remoteAddress);

    }
}
