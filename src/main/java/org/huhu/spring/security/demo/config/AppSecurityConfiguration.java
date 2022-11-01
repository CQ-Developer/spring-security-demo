package org.huhu.spring.security.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.oauth2.client.CommonOAuth2Provider.GITHUB;

@Configuration
public class AppSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.oauth2Login();
        httpSecurity.authorizeRequests()
                    .anyRequest()
                    .authenticated();
        return httpSecurity.build();
    }

    private ClientRegistration clientRegistration() {
        return GITHUB.getBuilder("github")
                     .clientId("147b7388f3f222a8a6cc")
                     .clientSecret("e74c289ab246d6dac1fc00c5790771d466ac3064")
                     .build();
    }

}
