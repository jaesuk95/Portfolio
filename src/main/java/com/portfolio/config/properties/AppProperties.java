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
    private final Server server = new Server();

    @Getter
    @Setter
    public static class Email {
        private String verification_link;
    }

    @Getter
    @Setter
    public static class Server {
        private String home;
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
