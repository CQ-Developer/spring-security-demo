package org.huhu.spring.security.demo.config;

import org.huhu.spring.security.demo.config.security.CsrfLoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppWebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic(Customizer.withDefaults())
                .addFilterAfter(new CsrfLoggingFilter(), CsrfFilter.class)
                .authorizeRequests(this::configureRequestMatcher)
                .build();
    }

    /**
     * 自定义授权规则
     */
    private void configureRequestMatcher(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry) {
        expressionInterceptUrlRegistry.anyRequest().authenticated();
    }

}
