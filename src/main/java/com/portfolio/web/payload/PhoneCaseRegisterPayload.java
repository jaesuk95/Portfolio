package com.portfolio.web.payload;

import com.portfolio.domain.common.PhoneCaseRegisterCommand;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PhoneCaseRegisterPayload {
    private String name;
    private Long imageId;
    private int price;
//    private List<String> phoneType;
    private String phoneType;
    private Long materialId;

    public PhoneCaseRegisterCommand toCommand() {
        return PhoneCaseRegisterCommand.builder()
                .phoneType(this.phoneType)
                .name(this.name)
                .imageId(this.imageId)
                .price(this.price)
                .materialId(this.materialId)
                .build();
    }
}
