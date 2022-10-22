package org.huhu.spring.security.demo.password.encoder.spring;

import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.util.Arrays;

/**
 * 演示如何使用 {@link BytesKeyGenerator} 生成盐值,
 * 生成一个长度为8的字节数组或一个指定长度的字节数组,
 * 每次都会生成不同的值.
 *
 * @see BytesKeyGenerator
 * @see KeyGenerators
 */
public class BytesKeyGeneratorDemo {

    public static void main(String[] args) {
        BytesKeyGenerator keyGenerator = KeyGenerators.secureRandom();

        // 盐值
        byte[] key = keyGenerator.generateKey();
        System.out.println(Arrays.toString(key));

        // 盐值的长度
        int keyLength = keyGenerator.getKeyLength();
        System.out.println(keyLength);

        // 指定长度的 BytesKeyGenerator
        BytesKeyGenerator lengthKeyGenerator = KeyGenerators.secureRandom(16);

        // 盐值
        byte[] salt = lengthKeyGenerator.generateKey();
        System.out.println(Arrays.toString(salt));

        // 盐值的长度
        int saltLength = lengthKeyGenerator.getKeyLength();
        System.out.println(saltLength);
    }

}
