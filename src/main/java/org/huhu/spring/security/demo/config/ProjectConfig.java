package org.huhu.spring.security.demo.config;

import org.huhu.spring.security.demo.service.CustomizedAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    /**
     * 该配置仅允许访问H2数据库控制台
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
            CustomizedAuthenticationProvider authenticationProvider) throws Exception {
        // 配置表单登录
        httpSecurity.formLogin()
                    .defaultSuccessUrl("/main", true);
        // 配置需要认证的请求路径
        httpSecurity.authorizeRequests()
                    .antMatchers("/h2", "/h2/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated();
        // 配置H2数据库允许访问
        httpSecurity.csrf()
                    .ignoringAntMatchers("/h2/**");
        // 配置H2数据库允许访问
        httpSecurity.headers()
                    .frameOptions()
                    .sameOrigin();
        // 配置认证逻辑
        httpSecurity.authenticationProvider(authenticationProvider);
        // 构建配置并返回
        return httpSecurity.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SCryptPasswordEncoder sCryptPasswordEncoder() {
        return new SCryptPasswordEncoder();
    }

}
