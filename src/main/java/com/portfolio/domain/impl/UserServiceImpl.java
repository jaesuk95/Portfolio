package com.portfolio.domain.impl;

import com.portfolio.domain.common.UserRegistrationCommand;
import com.portfolio.domain.management.UserRegistrationManagement;
import com.portfolio.domain.model.user.User;
import com.portfolio.domain.model.user.UserRepository;
import com.portfolio.domain.model.user.UserService;
import com.portfolio.domain.model.user.verify.EmailConfirmation;
import com.portfolio.domain.model.user.verify.EmailConfirmationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final EmailConfirmationRepository emailConfirmationRepository;
    private final UserRegistrationManagement userRegistrationManagement;
    @Override
    public void register(UserRegistrationCommand command) {

        User user = userRegistrationManagement.register(command);

        String email_token = UUID.randomUUID().toString();
        EmailConfirmation emailToken = createEmailToken(user, email_token);
        emailConfirmationRepository.save(emailToken);

        // rabbitMQ 한테 던진다 -> sms Server 에서 받아서 사용자에게 이메일 (node mailer)
    }

    private EmailConfirmation createEmailToken(User user, String token) {
        return new EmailConfirmation(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(20),
                user);
    }
}
