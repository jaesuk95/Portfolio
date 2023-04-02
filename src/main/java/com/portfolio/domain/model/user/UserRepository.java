package com.portfolio.domain.model.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAddress(String emailAddress);

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u " +
            "WHERE u.id = :admin_id AND " +
            "u.role = 'ROLE_ADMIN'")
    User findAdminJPQL(@Param("admin_id") Long admin_id);
}
