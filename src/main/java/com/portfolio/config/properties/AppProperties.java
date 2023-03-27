package com.portfolio.config.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private final Email email = new Email();
    private final Auth auth = new Auth();

    @Getter
    @Setter
    public static class Email {
        private String verification_link;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Auth {
        private String tokenSecret;
        private int tokenExpiryMin;
        private int refreshTokenExpiryDay;
    }
}
