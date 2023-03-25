package com.portfolio.domain.common;

import com.portfolio.web.payload.UserOrderRegisterPayload;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class UserOrderRegisterCommand extends AnonymousCommand{
    private List<UserOrderRegisterPayload.OrderDetailPayload> detailPayloads = new ArrayList<>();
}
