package org.huhu.spring.security.demo.config;

import org.huhu.spring.security.demo.authentication.provider.OtpAuthenticationProvider;
import org.huhu.spring.security.demo.authentication.provider.UsernamePasswordAuthenticationProvider;
import org.huhu.spring.security.demo.filter.InitialAuthenticationFilter;
import org.huhu.spring.security.demo.filter.JwtAuthenticationFilter;
import org.huhu.spring.security.demo.service.AuthenticationServerProxy;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProjectConfiguration {

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationServerProxy authenticationServerProxy, AuthenticationEventPublisher authenticationEventPublisher) {
        UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider = new UsernamePasswordAuthenticationProvider(authenticationServerProxy);
        OtpAuthenticationProvider otpAuthenticationProvider = new OtpAuthenticationProvider(authenticationServerProxy);
        ProviderManager providerManager = new ProviderManager(usernamePasswordAuthenticationProvider, otpAuthenticationProvider);
        providerManager.setAuthenticationEventPublisher(authenticationEventPublisher);
        return providerManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception {
        httpSecurity.csrf()
                    .disable();
        httpSecurity.authorizeRequests()
                    .anyRequest()
                    .authenticated();
        httpSecurity.addFilterAt(new InitialAuthenticationFilter(authenticationManager), BasicAuthenticationFilter.class)
                    .addFilterAfter(new JwtAuthenticationFilter(), BasicAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

}
