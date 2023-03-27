package com.portfolio.domain.model.product.phonecase;

import com.portfolio.domain.common.querydsl.Querydsl4RepositorySupport;
import com.querydsl.core.types.Projections;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.portfolio.domain.model.product.phonecase.QPhoneCase.phoneCase;

@Repository
public class PhoneCaseQueryDslRepository extends Querydsl4RepositorySupport {

    public PhoneCaseQueryDslRepository() {
        super(PhoneCase.class);
    }

    public Page<PhoneCaseData> findAll(Pageable pageable, String type) {
        // 앞으로 할 계획
        // redis 캐싱

        return applyPagination(pageable, contentQuery -> contentQuery
                .select(Projections.bean(PhoneCaseData.class,
                        phoneCase.id,
                        phoneCase.phoneType,
                        phoneCase.name,
                        phoneCase.price,
                        phoneCase.modelName))
                .from(phoneCase)
                .where(phoneCase.sale.eq(true)));

    }
}
