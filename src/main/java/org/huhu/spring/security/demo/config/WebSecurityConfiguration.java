package org.huhu.spring.security.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // 关闭CSRF方便演示CORS
        httpSecurity.csrf()
                    .disable();
        // 允许对任何端点的访问
        httpSecurity.authorizeRequests()
                    .anyRequest()
                    .permitAll();
        return httpSecurity.build();
    }

}
