package org.huhu.spring.security.demo.conf;

import org.huhu.spring.security.demo.filter.CsrfTokenLoggerFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
public class AppWebSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.addFilterAfter(new CsrfTokenLoggerFilter(), CsrfFilter.class);
        httpSecurity.authorizeRequests().anyRequest().permitAll();
        return httpSecurity.build();
    }

}
