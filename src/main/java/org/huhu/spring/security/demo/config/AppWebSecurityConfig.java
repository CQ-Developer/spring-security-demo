package org.huhu.spring.security.demo.config;

import org.huhu.spring.security.demo.config.component.AppAuthenticationFailureHandler;
import org.huhu.spring.security.demo.config.component.AppAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppWebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .formLogin(this::configureFormLogin)
                .authorizeRequests(this::configureRequestMatcher)
                .build();
    }

    private void configureFormLogin(FormLoginConfigurer<HttpSecurity> formLoginConfigurer) {
        formLoginConfigurer
                .successHandler(new AppAuthenticationSuccessHandler())
                .failureHandler(new AppAuthenticationFailureHandler());
    }

    /**
     * 自定义授权规则
     */
    private void configureRequestMatcher(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry) {
        expressionInterceptUrlRegistry.anyRequest().authenticated();
    }

}
