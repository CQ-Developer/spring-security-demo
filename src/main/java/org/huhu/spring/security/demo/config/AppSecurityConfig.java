package org.huhu.spring.security.demo.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

import static org.springframework.http.HttpMethod.GET;

@Configuration
public class AppSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 类比 {@link UserDetailsService}.
     *
     * <p>在 {@code Servlet} 环境中,
     * {@link AuthenticationManager} 将认证委派给 {@link AuthenticationProvider},
     * 再通过 {@link UserDetailsService} 找到指定用户.
     *
     * <p>在 {@code Reactive} 环境中,
     * {@link AuthenticationWebFilter} 将认证委派给 {@link ReactiveAuthenticationManager}
     * 并直接通过 {@link ReactiveUserDetailsService} 找到用户.
     *
     * @see AuthenticationWebFilter
     * @see ReactiveAuthenticationManager
     */
    @Bean
    public ReactiveUserDetailsService reactiveUserDetailsService() {
        UserDetails jack = User.withUsername("jack").password("123456").authorities("read").build();
        return new MapReactiveUserDetailsService(jack);
    }

    /**
     * 配置授权规则.
     *
     * <p>在 {@code Servlet} 环境中可以通过 {@link HttpSecurity} 配置授权规则.
     * 并通过将 {@link SecurityFilterChain} 作为 {@link Bean}
     * 发布到 {@link ApplicationContext} 中, 从而应用授权规则.
     *
     * <p>在 {@code Reactive} 环境中则是通过 {@link ServerHttpSecurity} 配置授权规则.
     * 并通过将 {@link SecurityWebFilterChain} 作为 {@link Bean}
     * 发布到 {@link ApplicationContext} 中, 从而应用授权规则.
     *
     * @see ServerHttpSecurity
     * @see SecurityWebFilterChain
     * @see HttpSecurity
     * @see SecurityFilterChain
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        return httpSecurity
                .httpBasic(Customizer.withDefaults())
                .authorizeExchange(this::authorizeExchange)
                .build();
    }

    private void authorizeExchange(ServerHttpSecurity.AuthorizeExchangeSpec exchangeMatcherRegistry) {
        exchangeMatcherRegistry
                .pathMatchers(GET, "/hello").authenticated()
                .anyExchange().permitAll();
    }

}
