package com.portfolio.domain.model.product.phonecase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PhoneCaseRepository extends JpaRepository<PhoneCase, Long> {
    @Query("select p from PhoneCase p where p.id = :id")
    Optional<PhoneCase> findByIdJPQL(@Param("id") Long id);
}
