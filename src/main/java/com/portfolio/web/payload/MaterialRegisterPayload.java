package com.portfolio.web.payload;

import com.portfolio.domain.common.MaterialRegisterCommand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaterialRegisterPayload {
    private String materialName;
    private String surfaceName;

    public MaterialRegisterCommand toCommand() {
        return MaterialRegisterCommand.builder()
                .materialName(this.materialName)
                .surfaceName(this.surfaceName)
                .build();
    }
}
