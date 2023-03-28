package com.portfolio.web.apis;

import com.portfolio.domain.common.PhoneCaseRegisterCommand;
import com.portfolio.domain.common.PhoneCaseSearchCommand;
import com.portfolio.domain.common.ProductSearchCommand;
import com.portfolio.domain.common.response.SimpleResponseData;
import com.portfolio.domain.common.restpage.RestPage;
import com.portfolio.domain.model.product.phonecase.PhoneCaseData;
import com.portfolio.domain.model.product.phonecase.PhoneCaseService;
import com.portfolio.web.payload.PhoneCaseRegisterPayload;
import com.portfolio.web.results.ApiResult;
import com.portfolio.web.results.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class PhoneCaseApi extends AbstractBaseController {

    private final PhoneCaseService phoneCaseService;

    // 디자인 조회
    @GetMapping("/api/public/case")
    public ResponseEntity<ApiResult> getProducts(
            Pageable pageable, HttpServletRequest request, String type) {
        try {
            ProductSearchCommand command = ProductSearchCommand.builder()
                    .pageable(pageable)
                    .type(type)
                    .build();
            addTriggeredBy(command, request);
            RestPage<PhoneCaseData> sales = phoneCaseService.findAll(command);
            return Result.ok(ApiResult.list(sales));
        } catch (Exception e) {
            log.error("Unexpected error at GET /api/public/case");
            return Result.failure(e.getMessage());
        }
    }



}
