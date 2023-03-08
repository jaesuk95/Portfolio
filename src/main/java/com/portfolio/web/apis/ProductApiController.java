package com.portfolio.web.apis;

import com.portfolio.domain.common.ProductSearchCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class ProductApiController extends AbstractBaseController{



    @GetMapping("/api/product")
    public ResponseEntity.BodyBuilder getProducts(
            Pageable pageable, HttpServletRequest request) {
        ProductSearchCommand command = ProductSearchCommand.builder()
                .pageable(pageable)
                .build();
        addTriggeredBy(command,request);
        return ResponseEntity.ok();
    }
}
