package com.portfolio.domain.impl;

import com.portfolio.domain.common.PhoneCaseRegisterCommand;
import com.portfolio.domain.common.ProductSearchCommand;
import com.portfolio.domain.model.product.phonecase.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PhoneCaseServiceImpl implements PhoneCaseService {

    private final PhoneCaseQueryDslRepository phoneCaseQueryDslRepository;
    private final PhoneCaseRepository phoneCaseRepository;

    @Override
    public void findAll(ProductSearchCommand command) {
        phoneCaseQueryDslRepository.findAll(command.getPageable(), command.getType());
    }

    @Override
    public Long registerByAdmin(PhoneCaseRegisterCommand command) {
        // image 앞으로 추가될 예정
        Long imageId = command.getImageId();
        int price = command.getPrice();

        // 상품 이름 중보확인
        String name = command.getName();



        String type = command.getPhoneType().toLowerCase();

        PhoneType phoneType = PhoneType.valueOf(type);

        PhoneCase phoneCase = new PhoneCase(name, price, phoneType);
        phoneCaseRepository.save(phoneCase);

        phoneCase.setModelName(phoneCase.getId());

        log.info("New Phone-case has been registered case id = {}", phoneCase.getId());
        return phoneCase.getId();
    }

}
