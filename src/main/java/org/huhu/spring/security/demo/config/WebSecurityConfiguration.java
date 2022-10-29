package org.huhu.spring.security.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        // CORS配置
        httpSecurity.cors()
                    .configurationSource(this::customizeCorsConfiguration);
        return httpSecurity.build();
    }

    /**
     * 通过 {@link org.springframework.security.config.annotation.web.configurers.CorsConfigurer}
     * 配置 {@link org.springframework.web.cors.CorsConfigurationSource} 对象,
     * 实现自定义 CORS 配置.
     *
     * @see org.springframework.web.cors.CorsConfigurationSource
     * @see org.springframework.web.cors.CorsConfiguration
     */
    private CorsConfiguration customizeCorsConfiguration(HttpServletRequest request) {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:8080"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        return corsConfiguration;
    }

}
