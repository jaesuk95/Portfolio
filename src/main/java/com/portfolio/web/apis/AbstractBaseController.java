package com.portfolio.web.apis;

import com.portfolio.domain.common.AnonymousCommand;
import com.portfolio.domain.common.UserCommand;
import com.portfolio.domain.model.user.Role;
import com.portfolio.domain.model.user.SimpleUser;
import com.portfolio.utils.RequestUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractBaseController {
    // 회원 받기
    static void addTriggeredBy(UserCommand command, HttpServletRequest request) {
        Assert.notNull(request.getUserPrincipal(), "UserPrincipal 이 요청에 있어야 합니다.");
        SimpleUser simpleUser = getSimpleUser(request);
        command.setAdmin(simpleUser.isAdmin());
        Cookie clientId = WebUtils.getCookie(request, "_uuid");     // 프론트에서 쿠키를 생성한다. 그것이 바로 clientId
        command.triggeredBy(simpleUser.getUserId(), RequestUtils.getIpAddress(request),clientId.getValue());
    }

    // 회원/비회원 받기
    void addTriggeredBy(AnonymousCommand command, HttpServletRequest request) {
        if (request.getUserPrincipal() != null){
            SimpleUser simpleUser = getSimpleUser(request);
            command.setAdmin(simpleUser.isAdmin());
            command.setUserId(simpleUser.getUserId().value());
        }
        Cookie clientId = WebUtils.getCookie(request, "_uuid");
        command.triggeredBy(RequestUtils.getIpAddress(request), clientId.getValue());
    }


    private static SimpleUser getSimpleUser(HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Role> collect = authentication.getAuthorities().stream()
                .map(grantedAuthority -> Role.valueOf(grantedAuthority.getAuthority()))
                .collect(Collectors.toList());
        Role role = Role.getHighest(collect);

        SimpleUser simpleUser = (SimpleUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        simpleUser.setRole(role);

        return simpleUser;
    }
}
