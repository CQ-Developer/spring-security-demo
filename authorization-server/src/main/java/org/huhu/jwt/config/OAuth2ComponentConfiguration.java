package org.huhu.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
public class OAuth2ComponentConfiguration {

    private final String jwtPriKey =
            "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQClJVxZidL026xs" +
            "izT8fQFSM0tOyIh16D9prmcWvzr8HGvFbbXhhZi4fK5MzlccwP0sDFtEuCJAkbXf" +
            "GbW5l9hd/4z9UZlEKc8RV2KvQ3tgVuTUJ1ZzBR8Zmt+iDH3SQ2dxpQSTqWyEKTA+" +
            "oFq989PkGTM9WPxe1GbPqGSZdx4zLyXuq8IZOXv7Acm2OQkJRGw5fe5jrtNXeWD8" +
            "5nqyZlpBzpeXylWceqrH1vOL/BT4Ck6odkHJE1YFH5Xk0gSyTEo/R9U9+MY+qiSY" +
            "KRsPcR//wf/hF9BnvNLHM1/j0DZqUWsKrYXSQLRNBUCvdJ1I9fx8XMdNUVdd1sbE" +
            "sShS77szAgMBAAECggEAFFh3+if6r2Po6C52Rqz33rKnEnuuJCiKt3BfDvRcRfcS" +
            "Z+5OPAyDdicWKiw5mzdDeYvEKsEspxvMdkHqm4TTiTEUo5jL8xPbiN1lMQZ1Srcp" +
            "1eFKyzzUSglEh+hWD/Nx6RRVbgOvdNDGOtDynPhPIIjiQSqXXO96AxdR3X5JYDC5" +
            "xomcYfhQUgPLA+NgF82ZdaJsd3Qu2mFMyJgw9dMAoAwo+C32OFtuUZYf3sK/E7uN" +
            "e4u9MaMqhATGKEzZ5Vk/ORw64cPt04hwDOLuQN+o+PnA+izjWJSEvBYK1sbgd9cP" +
            "HnIXMl8usCAzAyLKnFD3p7tBzhAZk9gyrgOryIrxAQKBgQDoZAn3lKSFAXrqbnXc" +
            "ptREvwOAG1JUSrhe60KTt0C1NoAkUwghRR4NBpF9LkU2db9NVl0AnlqIpQOoTmgv" +
            "4WEKq/bJzaW5sa2Htme3bNQrtVz8cZc8+j0K2hANG3kcu9d0E67YqbkumE7kBbWd" +
            "6Nvutf8wJBOGlCe3K+FNmP7zcwKBgQC17G9ZJeIxbnUlrei2+ApFsAC8upyY99KZ" +
            "oV4ZI/yJ3rOwc6evWmqsLlQQjzh9yc2pZRV9XbHipQ/fureiJHYrRZbMuRjpb87n" +
            "qu9kJhJmmtfbTA70RuJ5VoGnOSlnNqPZvzdQ4IsKMtBDEKwbv96SqtCzVpXICd2G" +
            "vnfoCU+pQQKBgQCQDAp9k36TD0LQc53NIv/qiPgzfTwAEbzM4UkIZQvQB7NQUMN2" +
            "U/B3pH2kOX0fb8cfugu16XI2xwpIVimQnERZqBQ2LKmwO+lTeQY7FVNCsI2t0P8t" +
            "BfO2eUp/xWrCG9FGGzlDBZlKv5APGVblRcSl59wBTCtqPQWNLb7fKZnG7wKBgHfB" +
            "NSclBfnuMVVlXqybKP/otQOHgDA96rdQ/0VjaAlFvVlpOLf4uAGx2YqxDMnY/vYE" +
            "YZoOBFwitglbuygUAmnHvFX1fo2kGTptTnrJiRFb2r40Gh/5TXn8VQrEWRnlYQSk" +
            "mAA4X66N9oQ/RFxb+GLp6Mo1NBHjVJ0vYnUvM+5BAoGBAL+ys8KeE/eYkUlbAl0T" +
            "9SwesramSBQoqzGmkvt7yhbd+8Hju2oroG5cKHcj2CM3cecLtRSxVcYlRwMZWnYS" +
            "d5s/V30ZdRivTnQjv0UYK21WB8TGYI/fFX9ZQCh9bTvp9WvixpRXEzwSMaDHSFnV" +
            "Wn4XFqsBNVXn9ewivoPJo9Gd";

    private final String jwtPubKey =
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApSVcWYnS9NusbIs0/H0B" +
            "UjNLTsiIdeg/aa5nFr86/BxrxW214YWYuHyuTM5XHMD9LAxbRLgiQJG13xm1uZfY" +
            "Xf+M/VGZRCnPEVdir0N7YFbk1CdWcwUfGZrfogx90kNncaUEk6lshCkwPqBavfPT" +
            "5BkzPVj8XtRmz6hkmXceMy8l7qvCGTl7+wHJtjkJCURsOX3uY67TV3lg/OZ6smZa" +
            "Qc6Xl8pVnHqqx9bzi/wU+ApOqHZByRNWBR+V5NIEskxKP0fVPfjGPqokmCkbD3Ef" +
            "/8H/4RfQZ7zSxzNf49A2alFrCq2F0kC0TQVAr3SdSPX8fFzHTVFXXdbGxLEoUu+7" +
            "MwIDAQAB";

    @Bean
    public JwtTokenStore jwtTokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(loadKeyPair());
        return jwtAccessTokenConverter;
    }

    private KeyPair loadKeyPair() {
        try {
            String alg = "RSA";
            KeyFactory keyFactory = KeyFactory.getInstance(alg);

            Base64.Decoder decoder = Base64.getDecoder();
            PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decoder.decode(jwtPriKey), alg));
            PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(decoder.decode(jwtPubKey), alg));

            return new KeyPair(publicKey, privateKey);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("加载密钥失败", e);
        }
    }

}
