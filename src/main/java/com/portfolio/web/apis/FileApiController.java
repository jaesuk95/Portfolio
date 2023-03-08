package com.portfolio.web.apis;

import com.portfolio.web.results.ApiResult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FileApiController extends AbstractBaseController{

    @PostMapping("/api/image")
    public ResponseEntity<ApiResult> uploadFile(
            @RequestParam("file")MultipartFile file, HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String uri = request.getRequestURI();
        return null;
    }
}
