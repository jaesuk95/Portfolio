package com.portfolio.domain.model.user.verify;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailConfirmationRepository extends JpaRepository<EmailConfirmation,Long> {
}
