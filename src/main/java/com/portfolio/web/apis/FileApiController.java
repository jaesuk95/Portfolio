package com.portfolio.web.apis;

import com.portfolio.domain.common.AttachmentUploadCommand;
import com.portfolio.domain.model.attachment.AttachmentData;
import com.portfolio.domain.model.attachment.AttachmentService;
import com.portfolio.web.results.ApiResult;
import com.portfolio.web.results.Result;
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
            @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            AttachmentUploadCommand command = new AttachmentUploadCommand(file);
            addTriggeredBy(command, request);
            AttachmentData attachmentData = attachmentService.uploadFile(command);
            return Result.ok(ApiResult.data(attachmentData));
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }
    // "http://localhost:8080/file/9820feff-46ff-4809-b911-625705389a9d-photo-1533450718592-29d45635f0a9.jpeg"
}
