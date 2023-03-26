package com.portfolio.domain.model.address;

import com.portfolio.domain.common.AddressRegisterCommand;
import com.portfolio.domain.common.AddressSearchCommand;

public interface AddressService {
    Long register(AddressRegisterCommand command);

    void getAddress(AddressSearchCommand command);
}
