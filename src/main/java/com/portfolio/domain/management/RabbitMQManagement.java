package com.portfolio.domain.management;

import com.portfolio.config.properties.EmailTemplateProperties;
import com.portfolio.domain.model.order.OrderDetail;
import com.portfolio.domain.model.order.UserOrder;
import com.portfolio.domain.model.rabbit.RabbitMQSender;
import com.portfolio.domain.model.rabbit.RabbitQueue;
import com.portfolio.domain.model.user.User;
import com.portfolio.infrastructure.mail.EmailTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQManagement {

    private final EmailTemplateProperties emailTemplateProperties;
    private final RabbitMQSender rabbitMQSender;
//    @Value("${email.senderEmailAddress}")
//    private String sender;

    private void sendMessage(String email, EmailTemplate emailTemplate, Map<String, Object> map, RabbitQueue queue) {
        rabbitMQSender.sendEmail(
                emailTemplateProperties.getSenderEmailAddress(),
                email,
                emailTemplate,
                map,
                queue
        );
    }

    public void sendWelcomeMessage(User user, String link) {
        Map<String, String> userData = new HashMap<>();
        userData.put("name", user.getUsername());
        userData.put("registerDate", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd")));

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("user", userData);
        sendMessage(user.getEmailAddress(),
                EmailTemplate.USER_REGISTER,
                userMap,
                RabbitQueue.USER_REGISTER);
    }

    public void sendUserOrderMessage(UserOrder userOrder) {
        List<OrderDetail> userOrderDetails = userOrder.getOrderDetailList();

        List<Map<String, String>> mapList = new ArrayList<>();

        for (OrderDetail userOrderDetail : userOrderDetails) {
            Map<String, String> orderDetailMap = new HashMap<>();
            orderDetailMap.put("addressDetail", userOrderDetail.getAddress().getAddressDetail());
            orderDetailMap.put("username", userOrderDetail.getAddress().getRecipientName());
            orderDetailMap.put("postal", userOrderDetail.getAddress().getZipcode());
            orderDetailMap.put("quantity", String.valueOf(userOrderDetail.getQuantity()));
            orderDetailMap.put("productPrice", String.valueOf(userOrderDetail.getProductPrice()));
            orderDetailMap.put("phone", userOrderDetail.getAddress().getRecipientPhone());
            orderDetailMap.put("productName", userOrderDetail.getProduct().getName());      // 이름에 추가하고 싶은데
            orderDetailMap.put("status", userOrderDetail.getDetailStatus().toString());
            mapList.add(orderDetailMap);
        }

        Map<String, Object> userOrderMap = new HashMap<>();

        userOrderMap.put("userOrderNumber", userOrder.getOrderNumber());
        userOrderMap.put("method", userOrder.getMethod());
        userOrderMap.put("totalPrice", String.valueOf(userOrder.getTotalPrice()));

        userOrderMap.put("title", userOrder.getUser().getUsername() + "주문내역");
        userOrderMap.put("status", userOrder.getOrderStatus().toString());
        userOrderMap.put("orderDetails", mapList);

        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("userOrder", userOrderMap);

        sendMessage(userOrder.getUser().getEmailAddress(),
                EmailTemplate.USER_PAYMENT,
                objectMap,
                RabbitQueue.ORDER);
    }
}
