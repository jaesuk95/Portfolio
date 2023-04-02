package com.portfolio.web.apis;

import com.portfolio.domain.common.AdminCustomCaseRegisterCommand;
import com.portfolio.domain.common.CustomCaseSearchCommand;
import com.portfolio.domain.common.UserCustomCaseRegisterCommand;
import com.portfolio.domain.common.restpage.RestPage;
import com.portfolio.domain.model.custom.CustomCaseData;
import com.portfolio.domain.model.custom.CustomCaseService;
import com.portfolio.web.payload.AdminCustomCaseRegisterPayload;
import com.portfolio.web.payload.UserCustomCaseRegisterPayload;
import com.portfolio.web.results.ApiResult;
import com.portfolio.web.results.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@Slf4j
@RequiredArgsConstructor
public class CustomCaseApiController extends AbstractBaseController {

    private final CustomCaseService customCaseService;

    @PostMapping("/api/admin/custom/case")
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

    @PostMapping("/api/custom/case")
    public ResponseEntity<ApiResult> registerByUser(
            @RequestBody UserCustomCaseRegisterPayload payload,
            HttpServletRequest request) {
        try {
            UserCustomCaseRegisterCommand command = payload.toCommand();
            addTriggeredBy(command, request);
            Long id = customCaseService.registerByUser(command);
            return Result.ok(String.valueOf(id));
        } catch (Exception e) {
            return Result.failure("실패");
        }
    }

    @GetMapping("/api/public/custom/case")
    public ResponseEntity<ApiResult> getPublicCustomCaseDesigns (
             @RequestParam(required = true) boolean adminTemplate,
             Pageable pageable,
             HttpServletRequest request) {
        CustomCaseSearchCommand command = CustomCaseSearchCommand.builder()
                .isAdminTemplate(adminTemplate)
                .pageable(pageable)
                .build();
        addTriggeredBy(command, request);
        RestPage<CustomCaseData> page = customCaseService.getPublicDesigns(command);
        return Result.ok(ApiResult.list(page));
    }

    @GetMapping("/api/custom/case/saved")
    public ResponseEntity<ApiResult> getMyDesigns(HttpServletRequest request) {
        try {
            CustomCaseSearchCommand command = CustomCaseSearchCommand.builder().build();
            addTriggeredBy(command, request);
            List<CustomCaseData> designs = customCaseService.getMyDesigns(command);
            return Result.ok(ApiResult.data(designs));
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }

}
