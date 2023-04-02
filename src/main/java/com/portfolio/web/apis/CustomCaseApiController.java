package com.portfolio.web.apis;

import com.portfolio.domain.common.AdminCustomCaseRegisterCommand;
import com.portfolio.domain.model.custom.CustomCaseService;
import com.portfolio.web.payload.AdminCustomCaseRegisterPayload;
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
public class CustomCaseApiController extends AbstractBaseController {

    private final CustomCaseService customCaseService;

    @PostMapping("/api/admin/custom")
    public ResponseEntity<ApiResult> registerCustomCase(
            @RequestBody AdminCustomCaseRegisterPayload payload,
            HttpServletRequest request) {
        try {
            AdminCustomCaseRegisterCommand command = payload.toCommand();
            addTriggeredBy(command, request);
            Long id = customCaseService.registerByAdmin(command);
            return Result.ok(String.valueOf(id));
        } catch (Exception e) {
            return Result.failure("실패");
        }
    }



}
