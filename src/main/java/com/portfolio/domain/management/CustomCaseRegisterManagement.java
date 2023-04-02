package com.portfolio.domain.management;

import com.portfolio.domain.common.UserCustomCaseRegisterCommand;
import com.portfolio.domain.model.attachment.Attachment;
import com.portfolio.domain.model.attachment.AttachmentRepository;
import com.portfolio.domain.model.custom.CustomCase;
import com.portfolio.domain.model.custom.CustomCaseRepository;
import com.portfolio.domain.model.product.phonecase.PhoneCase;
import com.portfolio.domain.model.product.phonecase.PhoneCaseRepository;
import com.portfolio.domain.model.user.User;
import com.portfolio.domain.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomCaseRegisterManagement {

    private final PhoneCaseRepository phoneCaseRepository;
    private final AttachmentRepository attachmentRepository;
    private final UserRepository userRepository;
    private final CustomCaseRepository customCaseRepository;

    public CustomCase registerAdminTemplate(Long phoneCaseId, String design_object, Long imageId, long admin_id) {

        Attachment attachment = attachmentRepository.findById(imageId).orElseThrow();
        PhoneCase phoneCase = phoneCaseRepository.findById(phoneCaseId).orElseThrow();
        User admin = userRepository.findAdminJPQL(admin_id);

        CustomCase customCase = new CustomCase(
                attachment,
                design_object,
                phoneCase,
                admin
        );
        customCase.adminCreation();
        customCase.publicOpenlyDesign();
        customCaseRepository.save(customCase);
        return customCase;
    }

    public CustomCase registerCustomCase(UserCustomCaseRegisterCommand command) {
        Attachment attachment = attachmentRepository.findById(command.getImageId()).orElseThrow();
        PhoneCase phoneCase = phoneCaseRepository.findById(command.getPhoneCaseId()).orElseThrow();
        User user = userRepository.findById(command.getUserId().value()).orElseThrow();

        CustomCase inheritCaseDesign = customCaseRepository.findById(command.getInheritDesignId()).orElseThrow();
        CustomCase originCaseDesign = customCaseRepository.findById(command.getOriginDesignId()).orElseThrow();

        CustomCase customCase = new CustomCase(
                attachment,
                command.getDesignObject(),
                phoneCase,
                user,
                originCaseDesign,
                inheritCaseDesign
        );
        customCaseRepository.save(customCase);
        return customCase;
    }
}
