package org.huhu.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.oauth2.core.AuthorizationGrantType.CLIENT_CREDENTIALS;

@Configuration
public class ResourceServerWebSecurityConfigurer {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                    .anyRequest().authenticated();

        httpSecurity.oauth2ResourceServer()
                    .jwt()
                    .jwkSetUri("http://localhost:8080/oauth/token_key");

        ClientRegistration clientRegistration = ClientRegistration.withRegistrationId("id")
                                                                  .clientId("client")
                                                                  .clientSecret("secret")
                                                                  .tokenUri("http://localhost:8080/oauth/token")
                                                                  .authorizationGrantType(CLIENT_CREDENTIALS)
                                                                  .jwkSetUri("http://localhost:8080/oauth/token_key")
                                                                  .build();
        InMemoryClientRegistrationRepository clientRegistrationRepository = new InMemoryClientRegistrationRepository(clientRegistration);

        httpSecurity.oauth2Client()
                    .clientRegistrationRepository(clientRegistrationRepository);

        return httpSecurity.build();
    }

}
