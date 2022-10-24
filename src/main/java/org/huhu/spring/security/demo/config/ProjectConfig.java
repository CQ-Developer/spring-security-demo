package org.huhu.spring.security.demo.config;

import org.huhu.spring.security.demo.authentication.entry.point.CustomizedAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic(this::customizeHttpBasic)
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .build();
    }

    /**
     * 对 HttpBasic 认证方式执行一些自定义配置项
     *
     * @see CustomizedAuthenticationEntryPoint
     */
    private void customizeHttpBasic(HttpBasicConfigurer<HttpSecurity> httpBasicConfigurer) {
        // 为验证失败的响应配置区域名称
        // 设置自定义AuthenticationEntryPoint
        httpBasicConfigurer
                .realmName("OTHER")
                .authenticationEntryPoint(new CustomizedAuthenticationEntryPoint());
    }

}
