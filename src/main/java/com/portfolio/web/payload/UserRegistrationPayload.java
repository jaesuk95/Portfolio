package com.portfolio.web.payload;

import com.portfolio.domain.common.UserRegistrationCommand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationPayload {
    private String username;
    private String emailAddress;
    private String password;

    public UserRegistrationCommand toCommand() {
        return UserRegistrationCommand.builder()
                .username(this.username)
                .emailAddress(this.emailAddress)
                .password(this.password)
                .build();
    }
}
