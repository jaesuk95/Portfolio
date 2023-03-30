package com.portfolio.domain.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaymentCommand extends AnonymousCommand{
    private String orderNumber;
    private String receiptNumber;
    private String cancelReason;
}
