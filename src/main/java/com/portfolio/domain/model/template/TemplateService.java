package com.portfolio.domain.model.template;

import com.portfolio.domain.common.TemplateRegisterCommand;

public interface TemplateService {
    void register(TemplateRegisterCommand command);
}
