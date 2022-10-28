package org.huhu.spring.security.demo.conf;

import org.huhu.spring.security.demo.servlet.filter.CustomizedRequestValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class AppSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // 在BasicAuthenticationFilter之前添加自定义的过滤器实现
        httpSecurity.addFilterBefore(new CustomizedRequestValidationFilter(), BasicAuthenticationFilter.class);

        // 关闭对所有请求的认证
        // 方便验证过滤器
        httpSecurity.authorizeRequests()
                    .anyRequest().permitAll();

        return httpSecurity.build();
    }

}
