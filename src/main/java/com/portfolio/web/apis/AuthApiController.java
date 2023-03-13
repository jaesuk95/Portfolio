package com.portfolio.web.apis;

import com.portfolio.domain.common.UserRegistrationCommand;
import com.portfolio.domain.model.user.UserService;
import com.portfolio.web.payload.UserRegistrationPayload;
import com.portfolio.web.results.ApiResult;
import com.portfolio.web.results.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AuthApiController extends AbstractBaseController{

    private final UserService userService;

    @PostMapping("/api/auth/registration")
    public ResponseEntity<ApiResult> registerUser(
            @RequestBody UserRegistrationPayload payload, HttpServletRequest request) {
        UserRegistrationCommand command = payload.toCommand();
        addTriggeredBy(command,request);
        userService.register(command);
        return Result.created();
    }
}
