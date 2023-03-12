package com.portfolio.domain.model.product.phonecase;

import com.portfolio.domain.common.PhoneCaseRegisterCommand;
import com.portfolio.domain.common.ProductSearchCommand;

public interface PhoneCaseService {
    void findAll(ProductSearchCommand command);

    Long registerByAdmin(PhoneCaseRegisterCommand command);
}
