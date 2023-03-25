package com.portfolio.domain.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddressRegisterCommand extends UserCommand {
    private String addressName;
    private String addressDetail;
    private String zipcode;
    private String recipientName;
    private String recipientPhone;
    private boolean isMainAddress;
}
