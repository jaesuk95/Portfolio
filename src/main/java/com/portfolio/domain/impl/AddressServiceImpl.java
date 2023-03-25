package com.portfolio.domain.impl;

import com.portfolio.domain.common.AddressRegisterCommand;
import com.portfolio.domain.model.address.Address;
import com.portfolio.domain.model.address.AddressRepository;
import com.portfolio.domain.model.address.AddressService;
import com.portfolio.domain.model.user.User;
import com.portfolio.domain.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

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
}
