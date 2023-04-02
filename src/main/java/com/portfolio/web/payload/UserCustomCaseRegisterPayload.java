package com.portfolio.web.payload;

import com.portfolio.domain.common.UserCustomCaseRegisterCommand;
import lombok.Getter;

@Getter
public class UserCustomCaseRegisterPayload {
    private Long imageId;
    private String designObject;
    private Long phoneCaseId;
    private Long inheritDesignId;
    private Long originDesignId;

    public UserCustomCaseRegisterCommand toCommand() {
        return UserCustomCaseRegisterCommand.builder()
                .imageId(this.imageId)
                .designObject(this.designObject)
                .phoneCaseId(this.phoneCaseId)
                .inheritDesignId(this.inheritDesignId)
                .originDesignId(this.originDesignId)
                .build();
    }
}
