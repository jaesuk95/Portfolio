package com.portfolio.web.payload;

import com.portfolio.domain.common.TemplateRegisterCommand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedisRegisterPayload {
    private String key;
    private String value;

    public TemplateRegisterCommand toCommand() {
        return TemplateRegisterCommand.builder()
                .key(this.key)
                .value(this.value)
                .build();
    }
}
