package com.portfolio.domain.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PhoneCaseRegisterCommand extends UserCommand{
    private String name;
    private Long imageId;
    private int price;
    private String phoneType;
}
