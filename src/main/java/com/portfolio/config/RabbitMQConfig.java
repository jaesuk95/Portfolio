package com.portfolio.config;

import com.portfolio.domain.model.rabbit.RabbitJacksonConverter;
import com.portfolio.domain.model.rabbit.RabbitQueue;
import com.portfolio.infrastructure.mail.Mail;
import com.portfolio.domain.model.rabbit.RabbitData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.host}")
    private String rabbitHost;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.port}")
    private String port;

    @Resource
    private RabbitProperties rabbitProperties;

    public static final String exchange = "spring-boot-exchange";   // queue 들을 매핑할 key 값


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        log.info("username:: {}", rabbitProperties.getUsername());
//        factory.setHost(rabbitHost);
//        factory.setUsername(username);
//        factory.setPassword(password);

        factory.setHost(rabbitHost);
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setPort(Integer.parseInt(port));
        factory.setCacheMode(CachingConnectionFactory.CacheMode.CHANNEL); // CHANNEL = 새로 생성할 필요 없이 기존에 사용하던 것을 재사용한다
        return factory;
    }

    @Bean
    public MessageConverter messageConverter(){
        Jackson2JsonMessageConverter jsonConverter = new Jackson2JsonMessageConverter(RabbitJacksonConverter.getInstance());
        jsonConverter.setClassMapper(classMapper());
        return jsonConverter;
    }

    // Maps to/from JSON using type information in the MessageProperties;
    // the default name of the message property containing the type is '__TypeId__'.
    // An optional property setDefaultType(Class) is provided that allows mapping to a statically defined type,
    // if no message property is found in the message properties.
    @Bean
    public DefaultClassMapper classMapper() {
        DefaultClassMapper classMapper = new DefaultClassMapper();
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("mail", Mail.class);
        idClassMapping.put("redisRegister", RabbitData.class);
//        idClassMapping.put("userOrder", UserOrderData.class);
        classMapper.setIdClassMapping(idClassMapping);
        return classMapper;
    }

    // 없으면 안된다. 이유: rabbitMQ 페이지에서 데이터를 읽을 때 필요
    @Bean
    public RabbitTemplate rabbitTemplate(MessageConverter messageConverter, ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.setExchange(exchange);
        return rabbitTemplate;
    }

    // RabbitMQ creation
    @Bean
    public TopicExchange rabbitExchange (RabbitAdmin rabbitAdmin){
        TopicExchange topicExchange = new TopicExchange(exchange);
        topicExchange.setAdminsThatShouldDeclare(rabbitAdmin);
        return topicExchange;
    }

    // RabbitMQ creation
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory); // ConnectionFactory 에서 어드민 권한을 가져오며 rabbitAdmin 객체 생성
        // exchange 생성
        rabbitAdmin.declareExchange(rabbitExchange(rabbitAdmin));
//        rabbitExchange(rabbitAdmin);  // exchange 등록
        // queue 자동 등록 어드민 권한으로. 그리고 등록한 큐를 바인등한다 (exchange 으로)
        for (RabbitQueue rabbitQueue : RabbitQueue.values()) {
            rabbitAdmin.declareQueue(new Queue(rabbitQueue.getQueueName(), true));  // Enum 값들을 rabbitMQ queue 에 등록
            rabbitAdmin.declareBinding(BindingBuilder.bind(new Queue(rabbitQueue.getQueueName(), true))
                    .to(rabbitExchange(rabbitAdmin)).with(rabbitQueue.getQueueName()));     // Enum 값들과 exchange 를 바인딩
            // 예를 들어, 'sample' queue 를 생성하면 'spring-boot-exchange' 와 binding 해준다.
        }
        rabbitAdmin.afterPropertiesSet();
        return rabbitAdmin;
    }


}
