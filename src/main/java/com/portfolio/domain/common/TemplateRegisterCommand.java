package com.portfolio.domain.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class TemplateRegisterCommand extends UserCommand{
    private String key;
    private String value;
}
