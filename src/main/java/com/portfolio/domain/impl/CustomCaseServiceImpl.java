package com.portfolio.domain.impl;

import com.portfolio.domain.common.AdminCustomCaseRegisterCommand;
import com.portfolio.domain.model.custom.CustomCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomCaseServiceImpl implements CustomCaseService {
    @Override
    public Long registerByAdmin(AdminCustomCaseRegisterCommand command) {

        Long phoneCaseId = command.getPhoneCaseId();
        String object = command.getObject();
        Long imageId = command.getImageId();
        long admin_id = command.getUserId().value();

        return null;
    }
}
