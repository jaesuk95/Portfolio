package com.portfolio.domain.common;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserCustomCaseRegisterCommand extends UserCommand{
    private Long imageId;
    private String designObject;
    private Long phoneCaseId;
    private Long inheritDesignId;
    private Long originDesignId;
}
