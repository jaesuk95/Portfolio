package com.portfolio.domain.model.user;

import lombok.Getter;

import java.util.List;

@Getter
public enum Role {
    ROLE_UNVERIFIED,
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_SUPER_ADMIN
    ;

    public static Role getHighest(List<Role> list) {
        list.sort((a1, a2) -> Integer.compare(getAssignedValue(a2), getAssignedValue(a1)));
        return list.get(0);
    }

    private static int getAssignedValue(Role role){
        switch (role) {
            case ROLE_UNVERIFIED:
                return 0;
            case ROLE_USER:
                return 1;
            case ROLE_ADMIN:
                return 2;
            case ROLE_SUPER_ADMIN:
                return 3;
            default:
                return Integer.MAX_VALUE;
        }
    }

}
