package com.portfolio.domain.model.bootpay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BootpayApiResultData {
    private String receipt_url;
    private String order_id;
    private int price;
    private int status;
    private String pg;
    private String pg_name;
    private String method;
    private String method_name;
    private String receipt_id;
    private String purchased_at;
    private String cancelled_at;
//    private boolean success;
}