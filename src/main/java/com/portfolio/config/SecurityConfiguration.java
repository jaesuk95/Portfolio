package com.portfolio.config;

import com.portfolio.domain.impl.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

/**
 * prePostEnabled = 스프링의 @PreAuthorize, @PreFilter, @PostAuthorize  @PostFilter 활성화 여부
 * securedEnabled = @Secured 어노테이션 활성화 여부
 * jsr250Enabed = @RoleAllowed 어노테이션 사용 활성화 여부
 * */
@EnableWebSecurity  // 기본적으로 web 보안을 활성화 하겠다는 의미
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // https://fntg.tistory.com/189
    private final CustomUserDetailsService userDetailsService;

    // https://www.baeldung.com/spring-boot-security-autoconfiguration

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public static final String[] PUBLIC = new String[]{
            "/api/public/**"};

    /*
     * UserDetailsService 설정
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .logout()
                .logoutUrl("/api/auth/logout")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                .and()
                .authorizeRequests()    // 요청 접근제한을 설정
                .antMatchers(PUBLIC).permitAll()
                .antMatchers("/api/**").hasAnyAuthority("ROLE_USER")
                .anyRequest().authenticated()
        ;
    }
}
