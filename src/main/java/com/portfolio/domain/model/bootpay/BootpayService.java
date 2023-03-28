package com.portfolio.domain.model.bootpay;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.portfolio.domain.model.order.UserOrder;

public interface BootpayService {
    BootpayApiResultData getVerificationData(String receiptId) throws JsonProcessingException;

    void bootpayCancel(String receiptId, UserOrder userOrder, String cancelReason, int cancelPrice) throws JsonProcessingException;
}
