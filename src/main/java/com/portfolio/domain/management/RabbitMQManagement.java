package com.portfolio.domain.management;

import com.portfolio.config.properties.EmailTemplateProperties;
import com.portfolio.domain.model.rabbit.RabbitMQSender;
import com.portfolio.domain.model.rabbit.RabbitQueue;
import com.portfolio.domain.model.user.User;
import com.portfolio.infrastructure.mail.EmailTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQManagement {

    private final EmailTemplateProperties emailTemplateProperties;
    private final RabbitMQSender rabbitMQSender;
//    @Value("${email.senderEmailAddress}")
//    private String sender;

    public void sendMessage(String email, EmailTemplate emailTemplate, Map<String, Object> map, RabbitQueue queue) {
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
}
