package com.portfolio.domain.impl;

import com.portfolio.domain.common.AddressRegisterCommand;
import com.portfolio.domain.common.AddressSearchCommand;
import com.portfolio.domain.model.address.*;
import com.portfolio.domain.model.user.User;
import com.portfolio.domain.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final AddressQueryDslRepository addressQueryDslRepository;

    @Override
    public Long register(AddressRegisterCommand command) {

        User user = userRepository.findById(command.getUserId().value()).orElseThrow();

        Address address = new Address(
                user,
                command.getAddressName(),
                command.getAddressDetail(),
                command.getZipcode(),
                command.getRecipientName(),
                command.getRecipientPhone(),
                command.isMainAddress()
        );

        addressRepository.save(address);

        return address.getId();
    }

    @Override
    public List<AddressData> getAddress(AddressSearchCommand command) {
        long user_id = command.getUserId().value();
        return addressQueryDslRepository.getAddresses(user_id);
    }
}
