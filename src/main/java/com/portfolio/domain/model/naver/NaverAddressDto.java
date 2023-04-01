package com.portfolio.domain.model.naver;

import lombok.Data;

import java.util.List;

@Data
public class NaverAddressDto {
    private List<String> ids;
    private String id;
    private String zipcode;
    private boolean zipcodeUsed;
    private String address1;
}
