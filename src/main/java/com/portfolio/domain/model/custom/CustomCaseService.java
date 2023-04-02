package com.portfolio.domain.model.custom;

import com.portfolio.domain.common.AdminCustomCaseRegisterCommand;
import com.portfolio.domain.common.CustomCaseSearchCommand;
import com.portfolio.domain.common.UserCustomCaseRegisterCommand;
import com.portfolio.domain.common.restpage.RestPage;

public interface CustomCaseService {
    Long registerByAdmin(AdminCustomCaseRegisterCommand command);

    Long registerByUser(UserCustomCaseRegisterCommand command);

    RestPage<CustomCaseData> getPublicDesigns(CustomCaseSearchCommand command);
}
