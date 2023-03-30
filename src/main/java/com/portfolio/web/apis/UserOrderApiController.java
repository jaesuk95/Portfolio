package com.portfolio.web.apis;

import com.portfolio.domain.common.PaymentCommand;
import com.portfolio.domain.common.UserOrderRegisterCommand;
import com.portfolio.domain.model.order.UserOrderService;
import com.portfolio.web.payload.PaymentPayload;
import com.portfolio.web.payload.UserOrderRegisterPayload;
import com.portfolio.web.results.ApiResult;
import com.portfolio.web.results.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserOrderApiController extends AbstractBaseController {

    private final UserOrderService userOrderService;

    @PostMapping("/api/public/order")
    public ResponseEntity<ApiResult> userOrder(
            @RequestBody UserOrderRegisterPayload payload,
            HttpServletRequest request) {
        try {
            UserOrderRegisterCommand command = payload.toCommand();
            addTriggeredBy(command, request);
            String orderNumber = userOrderService.registerOrder(command);
            return Result.ok(orderNumber);
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }

    // bootpay 테스트 코드 추가 예정
    @PostMapping("/api/order/payment")
    public ResponseEntity<ApiResult> payment(
            @RequestBody PaymentPayload payload, HttpServletRequest request) {
        try {
            PaymentCommand paymentCommand = payload.toCommand();
            addTriggeredBy(paymentCommand, request);
            userOrderService.validatePayment(paymentCommand);
            return Result.ok();
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }

    @PostMapping("/api/order/payment/cancel")
    public ResponseEntity<ApiResult> cancelPayment (
            @RequestBody PaymentPayload payload, HttpServletRequest request) {
        PaymentCommand paymentCommand = payload.toCommand();
        addTriggeredBy(paymentCommand,request);
        userOrderService.cancelPayment(paymentCommand);
        return Result.ok();
    }

    @PostMapping("/api/public/bootpay/webhook")
    public ResponseEntity<ApiResult> bootpayWebhook(HttpServletRequest request) {
        return null;
    }
}
