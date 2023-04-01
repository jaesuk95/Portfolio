package com.portfolio.domain.common;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminCustomCaseRegisterCommand extends UserCommand{
    private Long imageId;
    private String object;
    private Long phoneCaseId;
}
