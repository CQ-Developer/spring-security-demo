package org.huhu.spring.security.demo.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class CustomizedWithSecurityContextFactory implements WithSecurityContextFactory<WithCustomizedAuthentication> {

    @Override
    public SecurityContext createSecurityContext(WithCustomizedAuthentication annotation) {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        Authentication authentication = new UsernamePasswordAuthenticationToken(annotation.username(), null, null);
        securityContext.setAuthentication(authentication);
        return securityContext;
    }

}
