package org.huhu.spring.security.demo.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * {@link CsrfFilter} 使用 {@link CsrfTokenRepository} 管理 {@link CsrfToken}.
 *
 * <p>除了 {@link HttpMethod#GET}, {@link HttpMethod#OPTIONS}, {@link HttpMethod#HEAD},
 * {@link HttpMethod#TRACE} 之外的其他其他请求, 必须携带由 {@link CsrfTokenRepository}
 * 生成的名为 {@code X-CSRF-TOKEN} 的 {@link HttpHeaders} 才能访问端点.
 *
 * <p>{@link CsrfFilter} 默认使用的是 {@link HttpSessionCsrfTokenRepository} 实现.
 *
 * <p>可以在 {@link HttpSecurity} 中通过 {@link HttpSecurity#csrf()} 对CSRF进行配置.
 * <ul>
 *     <li>通过 {@link CsrfConfigurer#ignoringAntMatchers(String...)} 配置需要忽略CSRF的路径.
 *     <li>通过 {@link CsrfConfigurer#ignoringRequestMatchers(RequestMatcher...)} 配置需要忽略CSRF的路径
 * </ul>
 *
 * @see CsrfFilter
 * @see CsrfTokenRepository
 * @see CsrfToken
 */
@Configuration
public class AppWebSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf()
                    .ignoringAntMatchers("/ciao");
        httpSecurity.authorizeRequests().anyRequest().permitAll();
        return httpSecurity.build();
    }

}
