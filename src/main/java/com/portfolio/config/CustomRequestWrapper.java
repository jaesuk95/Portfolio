package com.portfolio.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CustomRequestWrapper extends HttpServletRequestWrapper {

    private List<Cookie> cookies;

    public CustomRequestWrapper(HttpServletRequest request, HttpServletResponse response) {
        super(request);
        blockedCookieMode(request); // 사용자가 모든 쿠키를 차단했을 경우

        if (request.getCookies() != null) {
            this.cookies = Arrays.asList(request.getCookies());
        }

        List<Cookie> collectedCookies = new ArrayList<>(cookies);
        List<String> collect = collectedCookies.stream()
                .map(Cookie::getName)
                .collect(Collectors.toList());

        if (!collect.contains("_uuid")) {
            String generatedUUID = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("_uuid",generatedUUID);
            cookie.setPath("/");
            collectedCookies.add(cookie);
            this.cookies = collectedCookies;
            response.addCookie(cookie);
        }

    }

    private void blockedCookieMode(HttpServletRequest request) {
        if (request.getCookies() == null) {
            List<Cookie> list = new ArrayList<>();
            String generatedUUID = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("_uuid",generatedUUID);
            cookie.setPath("/");
            list.add(cookie);
            this.cookies = list;
        }
    }

    public void addCookie(Cookie cookie) {
        this.cookies.add(cookie);
    }

    @Override
    public Cookie[] getCookies() {
        return this.cookies.toArray(new Cookie[0]);
    }

}
