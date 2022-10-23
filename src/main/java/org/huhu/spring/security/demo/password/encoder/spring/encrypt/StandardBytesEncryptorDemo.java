package org.huhu.spring.security.demo.password.encoder.spring.encrypt;

import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.util.Arrays;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 基于字节的标准加解密,
 * 注意每次加密的值结果不同.
 *
 * @see BytesEncryptor
 * @see Encryptors#standard(CharSequence, CharSequence)
 */
public class StandardBytesEncryptorDemo {

    public static void main(String[] args) {
        // 盐值
        String salt = KeyGenerators.string().generateKey();
        // 密码
        String password = "secret";
        // 生成BytesEncryptor
        BytesEncryptor encryptor = Encryptors.standard(password, salt);

        // 需要加密的文本
        String text = "Hello";

        // 加密
        byte[] encryptBytes = encryptor.encrypt(text.getBytes(UTF_8));
        System.out.println(Arrays.toString(encryptBytes));

        // 解密
        byte[] decryptBytes = encryptor.decrypt(encryptBytes);
        System.out.println(new String(decryptBytes));
    }

}
