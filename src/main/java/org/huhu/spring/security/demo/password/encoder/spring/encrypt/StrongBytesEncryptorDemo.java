package org.huhu.spring.security.demo.password.encoder.spring.encrypt;

import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.util.Arrays;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 基于字节的强加解密,
 * 注意每次加密的值结果不同.
 *
 * @see BytesEncryptor
 * @see Encryptors#stronger(CharSequence, CharSequence)
 */
public class StrongBytesEncryptorDemo {

    public static void main(String[] args) {
        String salt = KeyGenerators.string().generateKey();
        String password = "secret";

        BytesEncryptor strongerBytesEncryptor = Encryptors.stronger(password, salt);

        String text = "Hello";

        byte[] encryptBytes = strongerBytesEncryptor.encrypt(text.getBytes(UTF_8));
        System.out.println(Arrays.toString(encryptBytes));

        byte[] decryptBytes = strongerBytesEncryptor.decrypt(encryptBytes);
        System.out.println(new String(decryptBytes));
    }

}
