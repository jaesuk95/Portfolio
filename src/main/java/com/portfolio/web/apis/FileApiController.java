package com.portfolio.web.apis;

import com.portfolio.domain.common.AttachmentUploadCommand;
import com.portfolio.domain.model.attachment.AttachmentService;
import com.portfolio.web.results.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class FileApiController extends AbstractBaseController{

    private final AttachmentService attachmentService;

    @PostMapping("/api/image")
    public ResponseEntity<ApiResult> uploadFile(
            @RequestParam("file")MultipartFile file, HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String uri = request.getRequestURI();
        AttachmentUploadCommand command = new AttachmentUploadCommand(file);
        addTriggeredBy(command, request);
        attachmentService.uploadFile(command);
        return null;
    }
}
