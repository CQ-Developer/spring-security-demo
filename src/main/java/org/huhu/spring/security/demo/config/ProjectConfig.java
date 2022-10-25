package org.huhu.spring.security.demo.config;

import org.huhu.spring.security.demo.authentication.success.handler.CustomizedAuthenticationFailureHandler;
import org.huhu.spring.security.demo.authentication.success.handler.CustomizedAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class ProjectConfig {

    /**
     * 配置formLogin登录方式.
     *
     * 当用户未登录时, 将被转发到/login
     * 访问/logout将登出
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .formLogin(this::customizeFormLogin)
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .build();
    }

    /**
     * 通过 {@link AuthenticationSuccessHandler} 和 {@link AuthenticationFailureHandler},
     * 对FormLogin登录进行配置.
     *
     * @see CustomizedAuthenticationSuccessHandler
     * @see CustomizedAuthenticationFailureHandler
     */
    private void customizeFormLogin(FormLoginConfigurer<HttpSecurity> formLoginConfigurer) {
        formLoginConfigurer
                .successHandler(new CustomizedAuthenticationSuccessHandler())
                .failureHandler(new CustomizedAuthenticationFailureHandler());
    }

}
