package org.huhu.spring.security.demo.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public abstract class GenerateCodeUtil {

    public static String generateCode() {
        try {
            SecureRandom secureRandom = SecureRandom.getInstanceStrong();
            int code = secureRandom.nextInt(9000) + 1000;
            return String.valueOf(code);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Problem when generating the random code", e);
        }
    }

}
