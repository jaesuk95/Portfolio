package com.portfolio.domain.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Builder
@Getter
@Setter
public class ProductSearchCommand extends AnonymousCommand{
    private Long id;
    private Pageable pageable;
    private String type;
}
