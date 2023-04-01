package com.portfolio.factory;

import com.portfolio.domain.model.oauth.ProviderType;
import com.portfolio.domain.model.user.Role;
import com.portfolio.domain.model.user.SimpleUser;
import com.portfolio.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.util.List;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

@RequiredArgsConstructor
@Slf4j
public class WithAccountSecurityContextFactory implements WithSecurityContextFactory<WithUser> {

    @Override
    @SneakyThrows
    public SecurityContext createSecurityContext(WithUser withUser) {
        String name = withUser.value();
        Long userId = name.equals("admin") ? 1L : 2L;
        Role roleSet = name.equals("admin") ? Role.ROLE_ADMIN : Role.ROLE_USER;

        User user = new User(
                name,
                name+"@test.com",
                "MyPassword!@",
                roleSet,
                ProviderType.LOCAL);

        FieldUtils.writeField(user,"id",userId,true);
        SimpleUser simpleUser = new SimpleUser(user);
        List<GrantedAuthority> role = (List<GrantedAuthority>) simpleUser.getAuthorities();
        Authentication authentication = new UsernamePasswordAuthenticationToken(simpleUser, simpleUser.getPassword(), role);
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);

        return context;
    }

}
