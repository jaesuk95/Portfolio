package com.portfolio.domain.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.domain.model.bootpay.BootpayApiResultData;
import com.portfolio.domain.model.bootpay.BootpayGateway;
import com.portfolio.domain.model.bootpay.BootpayService;
import com.portfolio.domain.model.order.UserOrder;
import kr.co.bootpay.Bootpay;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BootpayServiceImpl implements BootpayService {

    private final BootpayGateway bootpayGateway;

    // 여기에 다 나와 있음
    //    https://github.com/bootpay/backend-java-example

    @Override
    public BootpayApiResultData getVerificationData(String receiptId) throws JsonProcessingException {
        Bootpay bootpay = bootpayGateway.getBootpay();
        bootpay.setToken(bootpayGateway.getToken());
        try {
            HashMap<String, Object> res = bootpay.getReceipt(receiptId);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.convertValue(res, BootpayApiResultData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void bootpayCancel(String receiptId, UserOrder userOrder, String cancelReason, int cancelPrice) throws JsonProcessingException {

    }
}
