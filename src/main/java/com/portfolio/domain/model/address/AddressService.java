package com.portfolio.domain.model.address;

import com.portfolio.domain.common.AddressRegisterCommand;
import com.portfolio.domain.common.AddressSearchCommand;

import java.util.List;

public interface AddressService {
    Long register(AddressRegisterCommand command);

    List<AddressData> getAddress(AddressSearchCommand command);
}
