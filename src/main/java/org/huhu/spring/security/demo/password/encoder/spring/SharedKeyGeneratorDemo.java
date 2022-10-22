package org.huhu.spring.security.demo.password.encoder.spring;

import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.util.Arrays;

/**
 * 演示如何使用 {@link BytesKeyGenerator} 生成盐值,
 * 生成一个指定长度的字节数组,
 * 每次都会生成相同的值.
 *
 * @see BytesKeyGenerator
 * @see KeyGenerators
 */
public class SharedKeyGeneratorDemo {

    public static void main(String[] args) {
        BytesKeyGenerator keyGenerator = KeyGenerators.shared(16);

        byte[] bytes1 = keyGenerator.generateKey();
        System.out.println(Arrays.toString(bytes1));

        byte[] bytes2 = keyGenerator.generateKey();
        System.out.println(Arrays.toString(bytes2));
    }

}
