package com.portfolio.domain.model.product.phonecase;

import com.portfolio.domain.common.querydsl.Querydsl4RepositorySupport;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class PhoneCaseQueryDslRepository extends Querydsl4RepositorySupport {

    public PhoneCaseQueryDslRepository() {
        super(PhoneCase.class);
    }

    public void findAll(Pageable pageable, String type) {
        // 앞으로 할 계획
        // redis 캐싱
    }
}
