package com.portfolio.domain.model.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.portfolio.domain.common.PaymentCommand;
import com.portfolio.domain.common.UserOrderRegisterCommand;

public interface UserOrderService {
    String registerOrder(UserOrderRegisterCommand command);

    void validatePayment(PaymentCommand paymentCommand) throws JsonProcessingException;
}
