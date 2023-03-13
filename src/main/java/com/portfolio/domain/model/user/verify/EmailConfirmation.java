package com.portfolio.domain.model.user.verify;

import com.portfolio.domain.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class EmailConfirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    public void setToken(String token) {
        this.token = token;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    //    @Enumerated(EnumType.STRING)
//    private ConfirmationMethod confirmationMethod;

    @ManyToOne
    @JoinColumn(nullable = false, name = "USER_ID")
    private User user;

    public EmailConfirmation(String token, LocalDateTime createdAt,
                             LocalDateTime expiresAt, User newUser) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.user = newUser;
//        this.confirmationMethod = method;
    }
}
