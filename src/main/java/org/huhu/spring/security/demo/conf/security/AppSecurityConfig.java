package org.huhu.spring.security.demo.conf.security;

import org.huhu.spring.security.demo.conf.security.filter.CustomizedStaticKeyAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;

@Configuration
public class AppSecurityConfig {

    /**
     * 需要注意的是 {@link HttpSecurity#addFilterAt(Filter, Class)} 方法虽然是在指定的位置上添加 {@link Filter},
     * 但并不会覆盖原有位置上的 {@link Filter}, 而是在一个位置上存在多个 {@link Filter},
     * 这种情况下, 同一个位置上的多个 {@link Filter} 并不能保证执行的顺序.
     * 所以不推荐在 {@link FilterChain} 的同一位置上添加过多的过滤器,
     * 类似于本案例中, 并不需要使用 {@link BasicAuthenticationFilter},
     * 所以并没有调用 {@link HttpSecurity#httpBasic()} 方法.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // 在BasicAuthenticationFilter的位置上添加自定义的认证过滤器
        httpSecurity.addFilterAt(new CustomizedStaticKeyAuthenticationFilter(), BasicAuthenticationFilter.class);

        // 关闭对所有请求的认证
        // 方便验证过滤器
        httpSecurity.authorizeRequests()
                    .anyRequest().permitAll();

        return httpSecurity.build();
    }

}
