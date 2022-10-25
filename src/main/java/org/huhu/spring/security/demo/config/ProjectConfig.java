package org.huhu.spring.security.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.web.SecurityFilterChain;

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
     * 使用 {@link FormLoginConfigurer} 进一步配置FormLogin登录方式
     */
    private void customizeFormLogin(FormLoginConfigurer<HttpSecurity> formLoginConfigurer) {
        formLoginConfigurer.defaultSuccessUrl("/home", true);
    }

}
