package com.portfolio.domain.model.user;

import com.portfolio.domain.common.LoginCommand;
import com.portfolio.domain.common.UserRegistrationCommand;
import com.portfolio.web.results.TokenData;

public interface UserService {
    void register(UserRegistrationCommand command);

    TokenData login(LoginCommand command);
}
