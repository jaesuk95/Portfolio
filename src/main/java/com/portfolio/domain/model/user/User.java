package com.portfolio.domain.model.user;

import com.portfolio.domain.model.address.Address;
import com.portfolio.domain.model.oauth.ProviderType;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME", nullable = false, length = 50, unique = true)
    private String username;

    @Column(name = "EMAIL_ADDRESS", nullable = false, length = 100)
    private String emailAddress;

    @Column(name = "PASSWORD", nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(fetch = FetchType.LAZY)
    private final List<Address> address = new ArrayList<>();

    private String oauthId;
    private int userStatus;
    private String phoneNumber;

    public UserId getUserId() {
        return new UserId(id);
    }

}
