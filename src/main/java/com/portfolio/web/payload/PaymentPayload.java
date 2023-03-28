package com.portfolio.web.payload;

import com.portfolio.domain.common.PaymentCommand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentPayload {
    private String orderNumber;
    private String receiptNumber;

    public PaymentCommand toCommand() {
        return PaymentCommand.builder()
                .orderNumber(this.orderNumber)
                .receiptNumber(this.receiptNumber)
                .build();
    }
}
