package com.portfolio.domain.common;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
@Builder
public class CustomCaseSearchCommand extends AnonymousCommand{
    private Pageable pageable;
    private boolean isAdminTemplate;
}
