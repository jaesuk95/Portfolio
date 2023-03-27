package com.portfolio.domain.impl;

import com.portfolio.domain.common.LoginCommand;
import com.portfolio.domain.common.UserRegistrationCommand;
import com.portfolio.domain.management.RabbitMQManagement;
import com.portfolio.domain.management.UserRegistrationManagement;
import com.portfolio.domain.model.jwt.RefreshToken;
import com.portfolio.domain.model.jwt.TokenProvider;

import com.portfolio.domain.model.jwt.TokenService;
import com.portfolio.domain.model.rabbit.RabbitMQSender;
import com.portfolio.domain.model.user.SimpleUser;
import com.portfolio.domain.model.user.User;
import com.portfolio.domain.model.user.UserRepository;
import com.portfolio.domain.model.user.UserService;
import com.portfolio.domain.model.user.verify.EmailConfirmation;
import com.portfolio.domain.model.user.verify.EmailConfirmationRepository;
import com.portfolio.web.results.TokenData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final RabbitMQSender rabbitMQSender;
    private final RabbitMQManagement rabbitMQManagement;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final TokenService tokenService;

    @Override
    public void register(UserRegistrationCommand command) {

        User user = userRegistrationManagement.register(command);

        String email_token = UUID.randomUUID().toString();
        EmailConfirmation emailToken = createEmailToken(user, email_token);
        emailConfirmationRepository.save(emailToken);

        // rabbitMQ 한테 던진다 -> sms Server 에서 받아서 사용자에게 이메일 (node mailer)
        rabbitMQManagement.sendWelcomeMessage(user,"http://127.0.0.1:8081/link-test");
    }

    @Override
    public TokenData login(LoginCommand command) {
        UsernamePasswordAuthenticationToken authenticationToken = command.toAuthentication();

        // 실제로 검증 사용자 비밀번호 체크가 이루어지는 부분
        // authenticate 메서드가 실행이 될 때 loadUserByUserName 가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);


        // 토큰 생성
        TokenData tokenData = tokenProvider.generateTokenData(authentication);

        RefreshToken refreshToken = RefreshToken.builder()
                .userId(((SimpleUser) authentication.getPrincipal()).getUserId().toString())
                .value(tokenData.getRefreshToken())
                .build();

        tokenService.saveRefreshToken(command.getClientId(), refreshToken);

        return tokenData;
    }

    private EmailConfirmation createEmailToken(User user, String token) {
        return new EmailConfirmation(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(20),
                user);
    }
}
