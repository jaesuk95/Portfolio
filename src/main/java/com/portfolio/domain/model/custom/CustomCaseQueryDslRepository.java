package com.portfolio.domain.model.custom;

import com.portfolio.domain.common.CustomCaseSearchCommand;
import com.portfolio.domain.common.querydsl.Querydsl4RepositorySupport;
import com.portfolio.domain.common.restpage.RestPage;
import com.querydsl.core.types.Projections;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

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

}
