package org.huhu.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuth2ComponentBeanConfigurer {

    private final String jwtPubKey =
            "-----BEGIN PUBLIC KEY-----" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApSVcWYnS9NusbIs0/H0B" +
            "UjNLTsiIdeg/aa5nFr86/BxrxW214YWYuHyuTM5XHMD9LAxbRLgiQJG13xm1uZfY" +
            "Xf+M/VGZRCnPEVdir0N7YFbk1CdWcwUfGZrfogx90kNncaUEk6lshCkwPqBavfPT" +
            "5BkzPVj8XtRmz6hkmXceMy8l7qvCGTl7+wHJtjkJCURsOX3uY67TV3lg/OZ6smZa" +
            "Qc6Xl8pVnHqqx9bzi/wU+ApOqHZByRNWBR+V5NIEskxKP0fVPfjGPqokmCkbD3Ef" +
            "/8H/4RfQZ7zSxzNf49A2alFrCq2F0kC0TQVAr3SdSPX8fFzHTVFXXdbGxLEoUu+7" +
            "MwIDAQAB" +
            "-----END PUBLIC KEY-----";

    @Bean
    public JwtTokenStore jwtTokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new AdditionalAccessTokenConverter();
        jwtAccessTokenConverter.setVerifierKey(jwtPubKey);
        return jwtAccessTokenConverter;
    }

}
