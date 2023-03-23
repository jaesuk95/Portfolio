package com.portfolio.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "email")
public class EmailTemplateProperties {
    private String senderEmailAddress;
    private String registerUserTemplate;

}
