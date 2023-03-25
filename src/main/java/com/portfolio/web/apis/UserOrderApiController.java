package com.portfolio.web.apis;

import com.portfolio.domain.common.UserOrderRegisterCommand;
import com.portfolio.domain.model.order.UserOrderService;
import com.portfolio.web.payload.UserOrderRegisterPayload;
import com.portfolio.web.results.ApiResult;
import com.portfolio.web.results.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
        UserOrderRegisterCommand command = payload.toCommand();
        addTriggeredBy(command,request);
        String orderNumber = userOrderService.registerOrder(command);
        return Result.ok(orderNumber);
    }

}
