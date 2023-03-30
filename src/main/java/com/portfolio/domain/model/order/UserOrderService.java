package com.portfolio.domain.model.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.portfolio.domain.common.PaymentCommand;
import com.portfolio.domain.common.UserOrderRegisterCommand;
import com.portfolio.domain.model.bootpay.PaymentResultData;

public interface UserOrderService {
    String registerOrder(UserOrderRegisterCommand command);

    void validatePayment(PaymentCommand paymentCommand) throws JsonProcessingException;

    PaymentResultData cancelPayment(PaymentCommand paymentCommand) throws JsonProcessingException;
}
