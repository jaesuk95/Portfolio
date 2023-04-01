package com.portfolio.domain.model.custom;

import com.portfolio.domain.common.AdminCustomCaseRegisterCommand;

public interface CustomCaseService {
    Long registerByAdmin(AdminCustomCaseRegisterCommand command);
}
