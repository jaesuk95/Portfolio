package com.portfolio.domain.common;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PhoneCaseSearchCommand extends AnonymousCommand{
    private Long id;
    private boolean sale;
}
