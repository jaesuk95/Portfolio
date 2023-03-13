package com.portfolio.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private final Email email = new Email();

    @Getter
    @Setter
    public static class Email {
        private String verification_link;
    }
}
