package com.portfolio.domain.impl;

import com.portfolio.domain.common.ProductSearchCommand;
import com.portfolio.domain.model.product.phonecase.PhoneCaseQueryDslRepository;
import com.portfolio.domain.model.product.phonecase.PhoneCaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PhoneCaseServiceImpl implements PhoneCaseService {

    private final PhoneCaseQueryDslRepository phoneCaseQueryDslRepository;

    @Override
    public void findAll(ProductSearchCommand command) {
        phoneCaseQueryDslRepository.findAll(command.getPageable(),command.getType());
    }

}
