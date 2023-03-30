package com.portfolio.domain.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.domain.common.PaymentCommand;
import com.portfolio.domain.model.bootpay.BootpayApiResultData;
import com.portfolio.domain.model.bootpay.BootpayGateway;
import com.portfolio.domain.model.bootpay.BootpayService;
import com.portfolio.domain.model.order.UserOrder;
import kr.co.bootpay.Bootpay;
import kr.co.bootpay.model.request.Cancel;
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
    public void bootpayCancel(UserOrder userOrder, PaymentCommand command) throws JsonProcessingException {
        Bootpay bootpay = bootpayGateway.getBootpay();
        bootpay.setToken(bootpayGateway.getToken());

        Cancel cancel = new Cancel();
        cancel.receiptId = command.getReceiptNumber();
        cancel.cancelUsername = userOrder.getUser().getUsername();
        cancel.cancelMessage = command.getCancelReason();

//        cancel.price = 1000.0; //부분취소 요청시
//        cancel.cancelId = "12342134"; //부분취소 요청시, 중복 부분취소 요청하는 실수를 방지하고자 할때 지정
//        RefundData refund = new RefundData(); // 가상계좌 환불 요청시, 단 CMS 특약이 되어있어야만 환불요청이 가능하다.
//        refund.account = "675601012341234"; //환불계좌
//        refund.accountholder = "홍길동"; //환불계좌주
//        refund.bankcode = BankCode.getCode("국민은행");//은행코드
//        cancel.refund = refund;

        try {
            HashMap<String, Object> res = bootpay.receiptCancel(cancel);
            if(res.get("error_code") == null) { //success
                long status = Math.round((Double) res.get("status"));   // res 에서 20.0 으로 넘어온다
                userOrder.setStatus(status);
            } else {
                System.out.println("receiptCancel false: " + res);
            }
            BootpayApiResultData bootpayResult = getResultFromBootpay(command.getReceiptNumber(), bootpay);

            userOrder.setBootPayCancelResults(
                    bootpayResult.getCancelled_at(),
                    (long) bootpayResult.getStatus()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BootpayApiResultData getResultFromBootpay(String receiptId, Bootpay bootpay) throws Exception {
        HashMap<String, Object> result = bootpay.getReceipt(receiptId);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.convertValue(result, BootpayApiResultData.class);
    }
}
