package com.portfolio.domain.model.attachment;

import com.portfolio.domain.common.AttachmentUploadCommand;

import java.io.IOException;

public interface AttachmentService {
    void uploadFile(AttachmentUploadCommand command) throws IOException;
}
