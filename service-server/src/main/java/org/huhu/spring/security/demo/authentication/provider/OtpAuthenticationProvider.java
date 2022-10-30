package org.huhu.spring.security.demo.authentication.provider;

import org.huhu.spring.security.demo.authentication.OtpAuthentication;
import org.huhu.spring.security.demo.service.AuthenticationServerProxy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class OtpAuthenticationProvider implements AuthenticationProvider {

    private final AuthenticationServerProxy authenticationServerProxy;

    public OtpAuthenticationProvider(AuthenticationServerProxy authenticationServerProxy) {
        this.authenticationServerProxy = authenticationServerProxy;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String code = String.valueOf(authentication.getCredentials());
        boolean passed = authenticationServerProxy.sendOTP(username, code);
        if (!passed) {
            throw new BadCredentialsException("Bad credentials.");
        }
        return new OtpAuthentication(username, code);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OtpAuthentication.class.isAssignableFrom(authentication);
    }

}
