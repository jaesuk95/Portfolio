package com.portfolio.domain.impl;

import com.portfolio.domain.common.AdminCustomCaseRegisterCommand;
import com.portfolio.domain.management.CustomCaseRegisterManagement;
import com.portfolio.domain.model.custom.CustomCase;
import com.portfolio.domain.model.custom.CustomCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomCaseServiceImpl implements CustomCaseService {

    private final CustomCaseRegisterManagement customCaseRegisterManagement;

    @Override
    public Long registerByAdmin(AdminCustomCaseRegisterCommand command) {

        Long phoneCaseId = command.getPhoneCaseId();
        String design_object = command.getObject();
        Long imageId = command.getImageId();
        long admin_id = command.getUserId().value();

        CustomCase customCase = customCaseRegisterManagement.registerAdminTemplate(
                phoneCaseId,
                design_object,
                imageId,
                admin_id);

        return customCase.getId();
    }
}