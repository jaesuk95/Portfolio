package com.portfolio.domain.model.custom;

import com.portfolio.domain.common.AdminCustomCaseRegisterCommand;
import com.portfolio.domain.common.UserCustomCaseRegisterCommand;

public interface CustomCaseService {
    Long registerByAdmin(AdminCustomCaseRegisterCommand command);

    Long registerByUser(UserCustomCaseRegisterCommand command);
}
