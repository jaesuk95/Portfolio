package com.portfolio.domain.model.product.phonecase;

import com.portfolio.domain.common.PhoneCaseRegisterCommand;
import com.portfolio.domain.common.PhoneCaseSearchCommand;
import com.portfolio.domain.common.ProductSearchCommand;
import com.portfolio.domain.common.response.SimpleResponseData;

public interface PhoneCaseService {
    void findAll(ProductSearchCommand command);

    Long registerByAdmin(PhoneCaseRegisterCommand command);

    SimpleResponseData updateSaleStatus(PhoneCaseSearchCommand id);
}
