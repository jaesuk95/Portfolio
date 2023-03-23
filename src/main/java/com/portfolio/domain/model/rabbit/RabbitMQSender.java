package com.portfolio.domain.model.rabbit;

import com.portfolio.infrastructure.mail.EmailTemplate;
import com.portfolio.infrastructure.mail.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitMQSender {

    private final AmqpTemplate amqpTemplate;

    // 쓰레드가 다른 쓰레드에서 작업이 완료될 때까지 기다릴 수 있도록 해주는 클래스
    private final CountDownLatch latch = new CountDownLatch(1);

    public void sendEmail(String sender, String receiver, EmailTemplate templateId, Map<String, Object> variable, RabbitQueue queue) {
        Mail mail = Mail.builder()
                .sender(sender)
                .receiver(receiver)
                .templateId(templateId)
                .variables(variable)
                .build();

        amqpTemplate.convertAndSend("spring-boot-exchange", queue.getQueueName(), mail);
        latch.countDown();  // latch 의 숫자 감소
//        latch.await();    // latch 의 숫자가 0이 될 때까지 대기
    }
}
