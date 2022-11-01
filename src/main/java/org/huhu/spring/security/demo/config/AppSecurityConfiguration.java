package org.huhu.spring.security.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.oauth2.core.AuthorizationGrantType.AUTHORIZATION_CODE;

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
        return ClientRegistration.withRegistrationId("github")
                                 .clientId("147b7388f3f222a8a6cc")
                                 .clientSecret("e74c289ab246d6dac1fc00c5790771d466ac3064")
                                 .scope("read:user")
                                 .authorizationUri("https://github.com/login/oauth/authorize")
                                 .tokenUri("https://github.com/login/oauth/access_token")
                                 .userInfoUri("https://api.github.com/user")
                                 .userNameAttributeName("id")
                                 .clientName("GitHub")
                                 .authorizationGrantType(AUTHORIZATION_CODE)
                                 .redirectUri("{baseUrl}/{action}/oauth2/code/{registrationId")
                                 .build();
    }

}
