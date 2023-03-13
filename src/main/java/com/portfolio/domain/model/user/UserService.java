package com.portfolio.domain.model.user;

import com.portfolio.domain.common.UserRegistrationCommand;

public interface UserService {
    void register(UserRegistrationCommand command);
}
