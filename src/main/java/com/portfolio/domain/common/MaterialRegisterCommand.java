package com.portfolio.domain.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MaterialRegisterCommand extends UserCommand{
    private String materialName;
    private String surfaceName;
}
