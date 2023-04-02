package com.portfolio.domain.model.custom;

import com.portfolio.domain.common.CustomCaseSearchCommand;
import com.portfolio.domain.common.querydsl.Querydsl4RepositorySupport;
import com.portfolio.domain.common.restpage.RestPage;
import com.portfolio.domain.model.attachment.AttachmentData;
import com.portfolio.domain.model.material.MaterialData;
import com.portfolio.domain.model.product.phonecase.PhoneCaseData;
import com.querydsl.core.types.Projections;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomCaseQueryDslRepository extends Querydsl4RepositorySupport {
    public CustomCaseQueryDslRepository() {
        super(CustomCase.class);
    }

    public RestPage<CustomCaseData> getPublicDesigns(CustomCaseSearchCommand command) {

        Page<CustomCaseData> pagination = applyPagination(command.getPageable(), contentQuery -> contentQuery
                .select(Projections.bean(CustomCaseData.class,
                        QCustomCase.customCase.id))
                .from(QCustomCase.customCase)
                .where(QCustomCase.customCase.supported.eq(true),
                        QCustomCase.customCase.adminTemplate.eq(command.isAdminTemplate()))
        );

        return new RestPage<>(pagination.getContent(), command.getPageable(), (int) pagination.getTotalElements());

    }

    public List<CustomCaseData> getMyDesigns(Long userId) {
        return getQueryFactory()
                .select(Projections.bean(CustomCaseData.class,
                        QCustomCase.customCase.object,
                        Projections.bean(AttachmentData.class,
                                QCustomCase.customCase.designImage.publicUrl
                        ).as("attachmentData"),
                        Projections.bean(PhoneCaseData.class,
                                QCustomCase.customCase.phoneCase.phoneType,
                                Projections.bean(MaterialData.class,
                                        QCustomCase.customCase.phoneCase.material.id,
                                        QCustomCase.customCase.phoneCase.material.materialName
                                ).as("materialData")
                        ).as("phoneCaseData"))
                )
                .from(QCustomCase.customCase)
                .where(QCustomCase.customCase.supported.eq(true),
                        QCustomCase.customCase.creator.id.eq(userId))
                .fetch();

    }
}
