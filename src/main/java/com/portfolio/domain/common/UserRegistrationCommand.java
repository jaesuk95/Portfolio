package com.portfolio.domain.common;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRegistrationCommand extends AnonymousCommand{
    private String username;
    private String emailAddress;
    private String password;
}
