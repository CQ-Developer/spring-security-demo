package org.huhu.spring.security.demo.authentication.success.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.CollectionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public class CustomizedAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (CollectionUtils.isEmpty(authorities)) {
            response.sendRedirect("/error");
        }
        // 需要用户具备read权限
        Optional<? extends GrantedAuthority> requiredAuthority = authorities.stream()
                .filter(i -> "read".equals(i.getAuthority()))
                .findAny();
        if (requiredAuthority.isPresent()) {
            response.sendRedirect("/home");
        } else {
            response.sendRedirect("/error");
        }
    }

}
