package org.huhu.spring.security.demo.password.encoder.customized;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义 {@link PasswordEncoder} 实现
 *
 * @see org.springframework.security.crypto.password.NoOpPasswordEncoder
 */
public class PlainTextPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.equals(encodedPassword);
    }

}
