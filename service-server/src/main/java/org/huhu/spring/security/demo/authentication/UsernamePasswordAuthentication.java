package org.huhu.spring.security.demo.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 使用用户名和密码进行验证.
 *
 * @see OtpAuthentication
 */
public class UsernamePasswordAuthentication extends UsernamePasswordAuthenticationToken {

    /**
     * 两个参数的构造器函数不会将 {@link org.springframework.security.core.Authentication} 对象设置为已通过验证
     *
     * @see UsernamePasswordAuthenticationToken#UsernamePasswordAuthenticationToken(Object, Object)
     * @see AbstractAuthenticationToken#setAuthenticated(boolean)
     */
    public UsernamePasswordAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    /**
     * 三个参数的构造器函数会将 {@link org.springframework.security.core.Authentication} 对象设置为已通过验证
     *
     * @see UsernamePasswordAuthenticationToken#UsernamePasswordAuthenticationToken(Object, Object, Collection)
     * @see AbstractAuthenticationToken#setAuthenticated(boolean)
     */
    public UsernamePasswordAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

}
