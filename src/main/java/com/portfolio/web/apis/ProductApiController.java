package com.portfolio.web.apis;

import com.portfolio.domain.common.ProductSearchCommand;
import com.portfolio.domain.model.product.phonecase.PhoneCaseService;
import com.portfolio.web.results.ApiResult;
import com.portfolio.web.results.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class ProductApiController extends AbstractBaseController{

    private final PhoneCaseService phoneCaseService;

    @GetMapping("/api/public/case")
    public ResponseEntity<ApiResult> getProducts(
            Pageable pageable, HttpServletRequest request, String type) {
        ProductSearchCommand command = ProductSearchCommand.builder()
                .pageable(pageable)
                .type(type)
                .build();
        addTriggeredBy(command,request);
        phoneCaseService.findAll(command);
        return Result.ok();
    }
}
