package com.portfolio.domain.model.bootpay;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.portfolio.domain.common.PaymentCommand;
import com.portfolio.domain.model.order.UserOrder;

public interface BootpayService {
    BootpayApiResultData getVerificationData(String receiptId) throws JsonProcessingException;

    void bootpayCancel(UserOrder userOrder, PaymentCommand command) throws JsonProcessingException;
}
