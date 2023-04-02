package com.portfolio.domain.model.custom;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.portfolio.domain.model.attachment.AttachmentData;
import com.portfolio.domain.model.product.phonecase.PhoneCaseData;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomCaseData {
    private Long id;

    private AttachmentData attachmentData;
    private PhoneCaseData phoneCaseData;
}
