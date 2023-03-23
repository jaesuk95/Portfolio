package com.portfolio.rabbit;

import com.portfolio.domain.management.RabbitMQManagement;
import com.portfolio.domain.model.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RabbitMQTest {

    @Autowired
    RabbitMQManagement rabbitMQManagement;

    @Test
    void insert_message() {
        User user = new User("name",
                "jaesuk@email.com",
                "asdf");

        rabbitMQManagement.sendWelcomeMessage(user,"link.com");
    }
}
