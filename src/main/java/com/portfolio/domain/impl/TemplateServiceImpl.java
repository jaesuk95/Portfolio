package com.portfolio.domain.impl;

import com.portfolio.domain.common.TemplateRegisterCommand;
import com.portfolio.domain.model.template.TemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TemplateServiceImpl implements TemplateService {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void register(TemplateRegisterCommand command) {

        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(command.getKey(),command.getValue());

    }


}
