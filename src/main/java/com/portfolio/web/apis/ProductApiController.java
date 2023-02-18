package com.portfolio.web.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProductApiController extends AbstractBaseController{

    @GetMapping("/api/product")
    public ResponseEntity.BodyBuilder get() {
        return ResponseEntity.ok();
    }
}
