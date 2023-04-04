package com.portfolio.web.apis;

import com.portfolio.domain.common.AdminCustomCaseRegisterCommand;
import com.portfolio.domain.common.MaterialRegisterCommand;
import com.portfolio.domain.common.PhoneCaseRegisterCommand;
import com.portfolio.domain.common.PhoneCaseSearchCommand;
import com.portfolio.domain.common.response.SimpleResponseData;
import com.portfolio.domain.model.custom.CustomCaseService;
import com.portfolio.domain.model.material.MaterialService;
import com.portfolio.domain.model.product.phonecase.PhoneCaseService;
import com.portfolio.web.payload.AdminCustomCaseRegisterPayload;
import com.portfolio.web.payload.MaterialRegisterPayload;
import com.portfolio.web.payload.PhoneCaseRegisterPayload;
import com.portfolio.web.results.ApiResult;
import com.portfolio.web.results.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminApiController extends AbstractBaseController {

    private final PhoneCaseService phoneCaseService;
    private final CustomCaseService customCaseService;
    private final MaterialService materialService;

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

    // 어드민 케이스 탬플릿 등록
    @PostMapping("/api/admin/case")
    public ResponseEntity<ApiResult> registerPhoneCase(
            @RequestBody PhoneCaseRegisterPayload payload,
            HttpServletRequest request) {
        try {
            PhoneCaseRegisterCommand command = payload.toCommand();
            addTriggeredBy(command, request);
            Long id = phoneCaseService.registerByAdmin(command);
            return Result.ok(String.valueOf(id));
        } catch (Exception e) {
            log.error("Unexpected error at POST /api/admin/case");
            return Result.failure(e.getMessage());
        }
    }

    // 판매 허가/중단
    @PutMapping("/api/admin/case/{id}/on-sale")
    public ResponseEntity<ApiResult> updateSaleStatus(
            @PathVariable Long id, @RequestParam boolean sale, HttpServletRequest request) {
        try {
            PhoneCaseSearchCommand command = PhoneCaseSearchCommand.builder()
                    .sale(sale)
                    .id(id)
                    .build();
            addTriggeredBy(command,request);
            SimpleResponseData responseData = phoneCaseService.updateSaleStatus(command);
            return Result.ok(ApiResult.data(responseData));
        } catch (Exception e) {
            log.error("Unexpected error at POST /api/admin/case/{id}/on-sale id = {}", id);
            return Result.failure(e.getMessage());
        }

    }

    @PostMapping("/api/admin/material")
    public ResponseEntity<ApiResult> registerMaterial(
            @RequestBody MaterialRegisterPayload payload, HttpServletRequest request) {
        try {
            MaterialRegisterCommand command = payload.toCommand();
            addTriggeredBy(command, request);
            Long id = materialService.registerByAdmin(command);
            return Result.ok(String.valueOf(id));
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }

}
