package com.portfolio.web.payload;

import com.portfolio.domain.common.UserOrderRegisterCommand;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserOrderRegisterPayload {

    private List<OrderDetailPayload> orderDetailPayloads = new ArrayList<>();

    public static class OrderDetailPayload {
        private Long productId;
        private Long addressId;
        private String optionJson;
    }

    public UserOrderRegisterCommand toCommand() {
        return UserOrderRegisterCommand.builder()
                .detailPayloads(this.orderDetailPayloads)
                .build();
    }
}
