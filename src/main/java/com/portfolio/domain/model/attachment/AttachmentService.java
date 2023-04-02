package com.portfolio.domain.model.attachment;

import com.portfolio.domain.common.AttachmentUploadCommand;

import java.io.IOException;

public interface AttachmentService {
    AttachmentData uploadFile(AttachmentUploadCommand command) throws IOException;
}
