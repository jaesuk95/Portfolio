package com.portfolio.domain.model.address;

import com.portfolio.domain.common.querydsl.Querydsl4RepositorySupport;
import com.querydsl.core.types.Projections;
import org.springframework.stereotype.Repository;

@Repository
public class AddressQueryDslRepository extends Querydsl4RepositorySupport {
    public AddressQueryDslRepository() {
        super(Address.class);
    }

    public void getAddresses(Long userId) {

    }
}
