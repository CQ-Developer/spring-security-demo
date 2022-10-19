package org.huhu.spring.security.demo.config;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 实例 {@link AuthenticationProvider} 的作用,
 * 忽略对 {@link UserDetailsService} 和 {@link PasswordEncoder} 的使用
 */
@Component
public class CustomizedAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取用户名和密码
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        // 验证密码
        // 这里通常是委派给UserDetailsService和PasswordEncoder
        if ("john".equals(username) && "123456".equals(password)) {
            return new UsernamePasswordAuthenticationToken(username, password, List.of());
        }
        else {
            throw new AuthenticationCredentialsNotFoundException("Error in authentication!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
