package com.portfolio.domain.management;

import com.portfolio.domain.common.UserRegistrationCommand;
import com.portfolio.domain.model.user.User;
import com.portfolio.domain.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserRegistrationManagement {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public User register(UserRegistrationCommand command) {
        String encoded_password = passwordEncoder.encode(command.getPassword());
        User user = new User(
                command.getUsername(),
                command.getEmailAddress(),
                encoded_password
        );
        userRepository.save(user);
        return user;
    }
}
