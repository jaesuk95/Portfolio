package com.portfolio.domain.model.ipaddress;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 생성 이유 : 부트페이 webhook 연결시 Anonymous 으로 접근하기 때문에
// 특정 아이피에게 접근 권한을 부여한다.
@Entity
@Getter
@NoArgsConstructor
public class AllowedIpAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String ipAddress;

    private String identityName;
    private boolean permission;
    private String usePurpose;

    public AllowedIpAddress(String identityName, String ipAddress, boolean permission, String usePurpose) {
        this.identityName = identityName;
        this.ipAddress = ipAddress;
        this.permission = permission;
        this.usePurpose = usePurpose;
    }
}
