package com.portfolio.domain.model.address;

import com.portfolio.domain.common.AddressRegisterCommand;

public interface AddressService {
    Long register(AddressRegisterCommand command);
}
