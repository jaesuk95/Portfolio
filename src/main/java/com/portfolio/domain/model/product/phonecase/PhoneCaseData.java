package com.portfolio.domain.model.product.phonecase;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class PhoneCaseData {
    private Long id;
    @Enumerated(EnumType.STRING)
    private PhoneType phoneType;
    private String name;
    private int price;
    private String modelName;
}
