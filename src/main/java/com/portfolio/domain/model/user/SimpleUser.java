package com.portfolio.domain.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class SimpleUser implements UserDetails, Serializable {

    private UserId userId;
    private String username;
    private String password;
    private Role role;
    private Map<String, Object> attributes;


    public SimpleUser(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
    }

    public SimpleUser(Long userId,String username, Role role) {
        this.userId = new UserId(userId);
        this.username = username;
        this.password = "";
        this.role = role;
    }

    public SimpleUser(User user, Map<String, Object> attributes) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.attributes = attributes;
    }

    public UserId getUserId() {
        return userId;
    }

    public boolean isOwner(Long id) {
        return id.equals(this.userId.value());
    }

    public boolean isAdmin(){
        return this.role == Role.ROLE_ADMIN;
    }

    public void setRole(Role role){
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> rolesList = new ArrayList<GrantedAuthority>();

        switch (role){
            case ROLE_ADMIN:
                rolesList.add(new SimpleGrantedAuthority(Role.ROLE_ADMIN.name()));
            case ROLE_USER:
                rolesList.add(new SimpleGrantedAuthority(Role.ROLE_USER.name()));
            case ROLE_UNVERIFIED:
                rolesList.add(new SimpleGrantedAuthority(Role.ROLE_UNVERIFIED.name()));
        }
        return rolesList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
