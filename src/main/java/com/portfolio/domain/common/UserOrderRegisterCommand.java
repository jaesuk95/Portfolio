package com.portfolio.domain.common;

import com.portfolio.web.payload.UserOrderRegisterPayload;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserOrderRegisterCommand extends UserCommand{
    private int totalPrice;
    private List<UserOrderRegisterPayload.OrderDetailPayload> detailCommands;
}
