package org.huhu.spring.security.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.web.cors.CorsConfiguration.ALL;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppWebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeRequests(this::configureRequestMatcher)
                .csrf(this::doConfigureCsrf)
                .cors(this::doConfigureCors)
                .build();
    }

    private void doConfigureCors(CorsConfigurer<HttpSecurity> corsConfigurer) {
        corsConfigurer.configurationSource(this::defineCorsConfiguration);
    }

    private CorsConfiguration defineCorsConfiguration(HttpServletRequest request) {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedMethods(List.of(ALL));
        corsConfiguration.setAllowedOrigins(List.of(ALL));
        return corsConfiguration;
    }

    /**
     * 关闭CSRF方便测试
     */
    private void doConfigureCsrf(CsrfConfigurer<HttpSecurity> csrfConfigurer) {
        csrfConfigurer.disable();
    }

    /**
     * 自定义授权规则
     */
    private void configureRequestMatcher(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry) {
        expressionInterceptUrlRegistry.anyRequest().permitAll();
    }

}
