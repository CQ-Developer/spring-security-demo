package org.huhu.spring.security.demo.password.encoder.customized;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 自定义一个SHA512的 {@link PasswordEncoder} 实现
 *
 * @see MessageDigest
 */
public class SHA512PasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        try {
            byte[] bytes = rawPassword.toString().getBytes(UTF_8);

            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] encodedPassword = md.digest(bytes);

            return new String(Hex.encode(encodedPassword));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }

}
