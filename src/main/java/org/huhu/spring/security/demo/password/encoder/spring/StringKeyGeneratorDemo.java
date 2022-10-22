package org.huhu.spring.security.demo.password.encoder.spring;

import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;

/**
 * 演示如何使用 {@link StringKeyGenerator} 生成盐值,
 * 生成一个16个字节长度的16进制编码的字符串,
 * 每次都会生成不同的值
 *
 * @see StringKeyGenerator
 * @see KeyGenerators
 */
public class StringKeyGeneratorDemo {

    public static void main(String[] args) {
        StringKeyGenerator keyGenerator = KeyGenerators.string();
        String salt = keyGenerator.generateKey();
        System.out.println(salt);
    }

}
