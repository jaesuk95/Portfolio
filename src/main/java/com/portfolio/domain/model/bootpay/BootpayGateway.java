package com.portfolio.domain.model.bootpay;

import kr.co.bootpay.Bootpay;

public interface BootpayGateway {
    public String getToken();

    public Bootpay getBootpay();

//    public HashMap<String, Object> findByReceiptId(String receiptId);
}
