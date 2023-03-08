package com.portfolio.domain.model.attachment;

import com.portfolio.domain.common.AttachmentUploadCommand;

public interface AttachmentService {
    void uploadFile(AttachmentUploadCommand command);
}
