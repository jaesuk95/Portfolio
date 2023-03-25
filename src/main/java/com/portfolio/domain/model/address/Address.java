package com.portfolio.domain.model.address;

import com.portfolio.domain.model.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User userId;

    private String addressName;
    private String addressDetail;
    private String zipcode;
    private String recipientName;
    private String recipientPhone;

    private boolean mainAddress;

    public Address(User user, String addressName, String addressDetail, String zipcode, String recipientName, String recipientPhone, boolean mainAddress) {
        this.userId = user;
        this.addressName = addressName;
        this.addressDetail = addressDetail;
        this.zipcode = zipcode;
        this.recipientName = recipientName;
        this.recipientPhone = recipientPhone;
        this.mainAddress = mainAddress;
    }
}
