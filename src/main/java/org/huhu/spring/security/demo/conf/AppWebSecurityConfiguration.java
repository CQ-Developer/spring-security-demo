package org.huhu.spring.security.demo.conf;

import org.huhu.spring.security.demo.filter.CsrfTokenLoggerFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

/**
 * {@link CsrfFilter} 使用 {@link CsrfTokenRepository} 管理 {@link CsrfToken}.
 *
 * <p>除了 {@link HttpMethod#GET}, {@link HttpMethod#OPTIONS}, {@link HttpMethod#HEAD},
 * {@link HttpMethod#TRACE} 之外的其他其他请求, 必须携带由 {@link CsrfTokenRepository}
 * 生成的名为 {@code X-CSRF-TOKEN} 的 {@link HttpHeaders} 才能访问端点.
 *
 * <p>{@link CsrfFilter} 默认使用的是 {@link HttpSessionCsrfTokenRepository} 实现.
 *
 * @see CsrfFilter
 * @see CsrfTokenRepository
 * @see CsrfToken
 */
@Configuration
public class AppWebSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.addFilterAfter(new CsrfTokenLoggerFilter(), CsrfFilter.class);
        httpSecurity.authorizeRequests().anyRequest().permitAll();
        return httpSecurity.build();
    }

}
