package org.huhu.spring.security.demo.password.encoder.spring.encrypt;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

/**
 * 基于字符串的标准加解密,
 * 注意每次加密的值结果不同.
 *
 * @see TextEncryptor
 * @see Encryptors#text(CharSequence, CharSequence)
 */
public class StandardTextEncryptorDemo {

    public static void main(String[] args) {
        // 盐值
        String salt = KeyGenerators.string().generateKey();
        // 密码
        String password = "secret";
        // 加密器
        TextEncryptor textEncryptor = Encryptors.text(password, salt);
        // 需要加密的文本
        String msg = "Hello";
        // 加密
        String encryptedMsg = textEncryptor.encrypt(msg);
        System.out.println(encryptedMsg);
        // 解密
        String decryptedMsg = textEncryptor.decrypt(encryptedMsg);
        System.out.println(decryptedMsg);
    }

}
