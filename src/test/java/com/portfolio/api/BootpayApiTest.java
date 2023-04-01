package com.portfolio.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.portfolio.domain.impl.BootpayServiceImpl;
import com.portfolio.domain.model.bootpay.BootpayApiResultData;
import com.portfolio.domain.model.order.UserOrder;
import com.portfolio.domain.model.order.UserOrderRepository;
import com.portfolio.utils.JsonUtils;
import com.portfolio.web.payload.PaymentPayload;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.Cookie;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("initDB")
public class BootpayApiTest {

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BootpayServiceImpl bootpayServiceMock;

    @Test
    void bootpay_api_test() throws Exception {
        String orderNumber = "";
        UserOrder userOrder = userOrderRepository.findByOrderNumberJPQL(orderNumber);
        String receiptId = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy-MM-dd"));

        PaymentPayload payload = new PaymentPayload();
        payload.setOrderNumber(orderNumber);
        payload.setReceiptNumber(receiptId);

        BootpayApiResultData bootpayApiResultData = new BootpayApiResultData(
                "https://app.bootpay.co.kr/bill/Q240ZFlZcFVW",
                orderNumber,
                5000,
                0,
                "inicis",
                "이니시스",
                "card_rebill_rest",
                "ISP / 앱카드결제",
                receiptId,
                "AAAA",
                ""
        );

        when(bootpayServiceMock.getVerificationData(any()))
                .thenReturn(bootpayApiResultData);

        mvc.perform(post("/api/order/bootpay")
                .contentType(MediaType.APPLICATION_JSON)
                .cookie(new Cookie("_uuid", "abc"))
                .content(JsonUtils.toJson(payload))
        ).andExpect(status().isOk()).andDo(print()).andReturn();
    }

}
