package com.portfolio.web.payload;

import com.portfolio.domain.common.AdminCustomCaseRegisterCommand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminCustomCaseRegisterPayload {
    private Long imageId;
    private String object;
    private Long phoneCaseId;

    public AdminCustomCaseRegisterCommand toCommand() {
        return AdminCustomCaseRegisterCommand.builder()
                .imageId(this.imageId)
                .object(this.object)
                .phoneCaseId(this.phoneCaseId)
                .build();
    }
}
