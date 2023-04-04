package com.portfolio.domain.impl;

import com.portfolio.domain.common.PhoneCaseRegisterCommand;
import com.portfolio.domain.common.PhoneCaseSearchCommand;
import com.portfolio.domain.common.ProductSearchCommand;
import com.portfolio.domain.common.response.SimpleResponseData;
import com.portfolio.domain.common.restpage.RestPage;
import com.portfolio.domain.model.attachment.Attachment;
import com.portfolio.domain.model.attachment.AttachmentRepository;
import com.portfolio.domain.model.material.Material;
import com.portfolio.domain.model.material.MaterialRepository;
import com.portfolio.domain.model.product.phonecase.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PhoneCaseServiceImpl implements PhoneCaseService {

    private final PhoneCaseQueryDslRepository phoneCaseQueryDslRepository;
    private final PhoneCaseRepository phoneCaseRepository;
    private final MaterialRepository materialRepository;
    private final AttachmentRepository attachmentRepository;

    @Cacheable(value = "PHONE_CASE:CACHE:PUBLIC",
            key = "'PAGE' + #command.pageable.pageNumber.toString()",
            cacheManager = "publicRedisCacheManager")
    @Override
    public RestPage<PhoneCaseData> findAll(ProductSearchCommand command) {
        return phoneCaseQueryDslRepository.findAll(command.getPageable(), command.getType());
    }

    @Override
    public Long registerByAdmin(PhoneCaseRegisterCommand command) {
        int price = command.getPrice();

        String name = command.getName();

        String type = command.getPhoneType().toUpperCase();
        PhoneType phoneType = PhoneType.valueOf(type);

        Long materialId = command.getMaterialId();
        Material material = materialRepository.findById(materialId).orElseThrow();
        Attachment attachment = attachmentRepository.findById(command.getImageId()).orElseThrow();

        PhoneCase phoneCase = new PhoneCase(
                name,
                price,
                phoneType,
                material,
                attachment
        );

        phoneCaseRepository.save(phoneCase);
        phoneCase.setModelName(phoneCase.getId());

        log.info("New Phone-case has been registered case id = {}", phoneCase.getId());
        return phoneCase.getId();
    }

    @Override
    public SimpleResponseData updateSaleStatus(PhoneCaseSearchCommand command) {
        SimpleResponseData responseData = new SimpleResponseData();
        Optional<PhoneCase> opt_phoneCase = phoneCaseRepository.findByIdJPQL(command.getId());
        if (opt_phoneCase.isEmpty()) {
            responseData.setStatus(400);
            responseData.setMessage("없음");
            return responseData;
        }

        PhoneCase phoneCase = opt_phoneCase.get();
        boolean sale = command.isSale();
        phoneCase.setSale(sale);

        responseData.setStatus(200);
        responseData.setMessage("sale status " + phoneCase.isSale());
        return responseData;
    }

}
