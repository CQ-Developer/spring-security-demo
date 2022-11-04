package org.huhu.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
public class ResourceServerWebSecurityConfigurer {

    private final String jwtPubKey =
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApSVcWYnS9NusbIs0/H0B" +
            "UjNLTsiIdeg/aa5nFr86/BxrxW214YWYuHyuTM5XHMD9LAxbRLgiQJG13xm1uZfY" +
            "Xf+M/VGZRCnPEVdir0N7YFbk1CdWcwUfGZrfogx90kNncaUEk6lshCkwPqBavfPT" +
            "5BkzPVj8XtRmz6hkmXceMy8l7qvCGTl7+wHJtjkJCURsOX3uY67TV3lg/OZ6smZa" +
            "Qc6Xl8pVnHqqx9bzi/wU+ApOqHZByRNWBR+V5NIEskxKP0fVPfjGPqokmCkbD3Ef" +
            "/8H/4RfQZ7zSxzNf49A2alFrCq2F0kC0TQVAr3SdSPX8fFzHTVFXXdbGxLEoUu+7" +
            "MwIDAQAB";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                    .anyRequest().authenticated();

        RSAPublicKey rsaPublicKey = loadRSAPublicKey();
        JwtDecoder jwtDecoder = NimbusJwtDecoder.withPublicKey(rsaPublicKey).build();

        httpSecurity.oauth2ResourceServer()
                    .jwt()
                    .decoder(jwtDecoder);

        return httpSecurity.build();
    }

    private RSAPublicKey loadRSAPublicKey() {
        try {
            String alg = "RSA";
            KeyFactory keyFactory = KeyFactory.getInstance(alg);

            Base64.Decoder decoder = Base64.getDecoder();
            KeySpec keySpec = new X509EncodedKeySpec(decoder.decode(jwtPubKey), alg);

            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

}
