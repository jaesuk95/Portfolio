package com.portfolio.domain.model.address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressData {
    private String addressName;
    private String addressDetail;
    private String zipcode;
    private String recipientName;
    private String recipientPhone;
    private boolean mainAddress;
}
