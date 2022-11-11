package org.huhu.spring.security.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.AuthorizeExchangeSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.OAuth2ResourceServerSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.OAuth2ResourceServerSpec.JwtSpec;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class AppSecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        return httpSecurity
                .oauth2ResourceServer(this::doConfigureOath2ResourceService)
                .authorizeExchange(this::doAuthorizeExchange)
                .build();
    }

    private void doConfigureOath2ResourceService(OAuth2ResourceServerSpec oAuth2ResourceServerSpec) {
        oAuth2ResourceServerSpec.jwt(this::doConfigureJwt);
    }

    private void doConfigureJwt(JwtSpec jwtSpec) {
        jwtSpec.jwkSetUri("http://localhost:8080/realms/master/protocol/openid-connect/certs");
    }

    private void doAuthorizeExchange(AuthorizeExchangeSpec exchangeMatcherRegistry) {
        exchangeMatcherRegistry.anyExchange().authenticated();
    }

}
