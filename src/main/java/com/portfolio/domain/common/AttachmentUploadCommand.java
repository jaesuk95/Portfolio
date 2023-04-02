package com.portfolio.domain.common;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class AttachmentUploadCommand extends AnonymousCommand{
    private final MultipartFile file;

    public AttachmentUploadCommand(MultipartFile file) {
        this.file = file;
    }
}
