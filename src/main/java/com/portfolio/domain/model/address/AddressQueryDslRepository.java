package com.portfolio.domain.model.address;

import com.portfolio.domain.common.querydsl.Querydsl4RepositorySupport;
import com.querydsl.core.types.Projections;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.portfolio.domain.model.address.QAddress.*;

@Repository
public class AddressQueryDslRepository extends Querydsl4RepositorySupport {
    public AddressQueryDslRepository() {
        super(Address.class);
    }

    public List<AddressData> getAddresses(Long userId) {
        return getQueryFactory()
                .select(Projections.bean(AddressData.class,
                        address.addressName,
                        address.addressDetail,
                        address.zipcode,
                        address.recipientName,
                        address.recipientPhone))
                .from(address)
                .where(address.userId.id.eq(userId))
                .fetch();
    }
}
