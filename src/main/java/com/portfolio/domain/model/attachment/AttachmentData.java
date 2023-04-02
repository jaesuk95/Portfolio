package com.portfolio.domain.model.attachment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttachmentData {
    private Long id;
    private String filePath;
    private String fileName;
    private String publicUrl;
    private String fileType;
}
