package org.huhu.spring.security.demo.conf.security;

import org.huhu.spring.security.demo.conf.security.filter.CustomizedAuthenticationLoggingFilter;
import org.huhu.spring.security.demo.conf.security.filter.CustomizedRequestValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class AppSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // 在BasicAuthenticationFilter之前添加自定义的过滤器, 验证是否存在请求头
        // 在BasicAuthenticationFilter之后添加自定义的过滤器, 记录请求头内容
        httpSecurity.addFilterBefore(new CustomizedRequestValidationFilter(), BasicAuthenticationFilter.class)
                    .addFilterBefore(new CustomizedAuthenticationLoggingFilter(), BasicAuthenticationFilter.class);

        // 关闭对所有请求的认证
        // 方便验证过滤器
        httpSecurity.authorizeRequests()
                    .anyRequest().permitAll();

        return httpSecurity.build();
    }

}
