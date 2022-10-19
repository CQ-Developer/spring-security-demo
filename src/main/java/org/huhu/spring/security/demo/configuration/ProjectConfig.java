package org.huhu.spring.security.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    /**
     * <p>自定义一个 {@link UserDetailsService} 用于获取用户信息,
     * 使用基于内存的实现 {@link InMemoryUserDetailsManager} 比较方便演示.
     *
     * <p>通过 {@link User} 创建一个用户并将其保存到 {@link UserDetailsService} 中
     */
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();

        // 创建用户
        UserDetails user = User.withUsername("john")
                .password("123456")
                .authorities("read")
                .build();

        userDetailsService.createUser(user);

        return userDetailsService;
    }

    /**
     * 为 {@link UserDetailsService} 配置一个密码编码器,
     * 该 {@link NoOpPasswordEncoder} 不会对密码进行任何编码,
     * 而只是简单的使用 {@link String#equals(Object)} 对密码进行验证.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        // 配置所有请求都要进行身份验证
        http.authorizeRequests().anyRequest().authenticated();
    }

}
