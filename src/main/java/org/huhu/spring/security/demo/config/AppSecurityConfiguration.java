package org.huhu.spring.security.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                    .anyRequest()
                    .authenticated();

        httpSecurity.oauth2ResourceServer()
                    .opaqueToken()
                    .introspectionUri("http://localhost:8080/oauth/check_token")
                    .introspectionClientCredentials("resourceserver", "resourceserversecret");

        return httpSecurity.build();
    }

}
