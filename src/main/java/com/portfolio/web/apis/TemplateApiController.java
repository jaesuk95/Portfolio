package com.portfolio.web.apis;

import com.portfolio.domain.common.TemplateRegisterCommand;
import com.portfolio.domain.model.template.TemplateService;
import com.portfolio.web.results.ApiResult;
import com.portfolio.web.results.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class TemplateApiController extends AbstractBaseController {

    private final TemplateService templateService;

    @PutMapping("/api/admin/template/email")
    public ResponseEntity<ApiResult> registerTemplate(
            @RequestParam String key, @RequestParam String value,
            HttpServletRequest request) {
        try {
            TemplateRegisterCommand command = new TemplateRegisterCommand(key, value);
            addTriggeredBy(command, request);
            templateService.register(command);
            return Result.ok();
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }

}
