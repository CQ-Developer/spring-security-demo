package org.huhu.spring.security.demo.authentication.provider;

import org.huhu.spring.security.demo.authentication.UsernamePasswordAuthentication;
import org.huhu.spring.security.demo.service.AuthenticationServerProxy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final AuthenticationServerProxy authenticationServerProxy;

    public UsernamePasswordAuthenticationProvider(AuthenticationServerProxy authenticationServerProxy) {
        this.authenticationServerProxy = authenticationServerProxy;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());
        authenticationServerProxy.sendAuth(username, password);
        return new UsernamePasswordAuthentication(username, password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthentication.class.isAssignableFrom(authentication);
    }

}
