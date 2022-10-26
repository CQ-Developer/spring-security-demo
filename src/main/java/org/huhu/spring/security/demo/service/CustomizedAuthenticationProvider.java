package org.huhu.spring.security.demo.service;

import lombok.RequiredArgsConstructor;
import org.huhu.spring.security.demo.constant.EncryptionAlgorithm;
import org.huhu.spring.security.demo.entity.spring.CustomizedUserDetails;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomizedAuthenticationProvider implements AuthenticationProvider {

    private final CustomizedJpaUserDetailsService userDetailsService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final SCryptPasswordEncoder sCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        CustomizedUserDetails userDetails = userDetailsService.loadUserByUsername(username);

        EncryptionAlgorithm algorithm = userDetails.user().getAlgorithm();
        if (algorithm == null) {
            throw new BadCredentialsException("Bad Credentials");
        }

        return switch (algorithm) {
            case BCRYPT -> checkPassword(userDetails, password, bCryptPasswordEncoder);
            case SCRYPT -> checkPassword(userDetails, password, sCryptPasswordEncoder);
        };
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private Authentication checkPassword(CustomizedUserDetails userDetails, String password, PasswordEncoder passwordEncoder) {
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        }
        else {
            throw new BadCredentialsException("Bad Credentials");
        }
    }

}

