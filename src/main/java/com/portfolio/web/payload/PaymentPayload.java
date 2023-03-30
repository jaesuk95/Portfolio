package com.portfolio.web.payload;

import com.portfolio.domain.common.PaymentCommand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentPayload {
    private String orderNumber;
    private String receiptNumber;

    private String cancelReason;

    public PaymentCommand toCommand() {
        return PaymentCommand.builder()
                .cancelReason(this.cancelReason)
                .orderNumber(this.orderNumber)
                .receiptNumber(this.receiptNumber)
                .build();
    }
}
