package com.portfolio.web.payload;

import com.portfolio.domain.common.AddressRegisterCommand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRegisterPayload {
    private String addressName;
    private String addressDetail;
    private String zipcode;
    private String recipientName;
    private String recipientPhone;
    private boolean isMainAddress;

    public AddressRegisterCommand toCommand() {
        return AddressRegisterCommand.builder()
                .addressName(this.addressName)
                .addressDetail(this.addressDetail)
                .zipcode(this.zipcode)
                .recipientName(this.recipientName)
                .recipientPhone(this.recipientPhone)
                .isMainAddress(this.isMainAddress)
                .build();
    }
}
