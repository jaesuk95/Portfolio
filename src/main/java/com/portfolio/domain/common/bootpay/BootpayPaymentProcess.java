package com.portfolio.domain.common.bootpay;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.portfolio.domain.common.PaymentCommand;
import com.portfolio.domain.model.bootpay.BootpayApiResultData;
import com.portfolio.domain.model.bootpay.BootpayService;
import com.portfolio.domain.model.order.UserOrder;
import com.portfolio.domain.model.order.UserOrderRepository;
import com.portfolio.domain.model.order.UserOrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
@Transactional(isolation = Isolation.SERIALIZABLE)
public class BootpayPaymentProcess {

    private final UserOrderRepository userOrderRepository;
    private final BootpayService bootpayService;

    public void validatePayment(UserOrder userOrder, PaymentCommand command) throws JsonProcessingException {
        BootpayApiResultData resultData = bootpayService.getVerificationData(command.getReceiptNumber());
        // needs to validate between the server's api and user's payload
        if (!resultData.getOrder_id().equals(userOrder.getOrderNumber())) {
            throw new IllegalArgumentException("주문번호 오류");
        }
        if (resultData.getPrice() != userOrder.getTotalPrice()) {
            throw new IllegalArgumentException("결제금액 오류");
        }
        if (!resultData.getReceipt_id().equals(command.getReceiptNumber())) {
            throw new IllegalArgumentException("영수증 오류");
        }

        userOrder.setBootPayResults(
                resultData.getReceipt_url(),
                (long) resultData.getStatus(),
                resultData.getPg(),
                resultData.getPg_name(),
                resultData.getMethod(),
                resultData.getMethod_name(),
                resultData.getReceipt_id(),
                resultData.getPurchased_at(),
                UserOrderStatus.결제완료
        );
    }
}
