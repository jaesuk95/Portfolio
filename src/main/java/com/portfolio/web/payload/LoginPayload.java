package com.portfolio.web.payload;

import com.portfolio.domain.common.LoginCommand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginPayload {
    private String email;
    private String password;

    public LoginCommand toCommand() {
        return new LoginCommand(
                email,
                password);
    }
}
