package com.portfolio.domain.model.order;

import com.portfolio.domain.common.UserOrderRegisterCommand;

public interface UserOrderService {
    String registerOrder(UserOrderRegisterCommand command);
}
