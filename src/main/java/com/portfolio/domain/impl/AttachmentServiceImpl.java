package com.portfolio.domain.impl;

import com.portfolio.domain.common.AttachmentUploadCommand;
import com.portfolio.domain.model.attachment.AttachmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AttachmentServiceImpl implements AttachmentService {


    @Override
    public void uploadFile(AttachmentUploadCommand command) {
        String filePath;
        String folder = "attachment";
        boolean thumbnailCreated = false;
    }
}
