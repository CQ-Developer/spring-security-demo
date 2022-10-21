package org.huhu.spring.security.demo.config;

import org.huhu.spring.security.demo.model.User;
import org.huhu.spring.security.demo.service.InMemoryUserDetailsService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * 测试自定义实现 {@link User} 和 {@link InMemoryUserDetailsService}
 *
 * 将 {@link InMemoryUserDetailsService} 注册到 {@link ApplicationContext} 中,
 * 并提供一个 {@link PasswordEncoder}
 *
 * @see User
 * @see InMemoryUserDetailsService
 */
@Configuration
public class ProjectConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        List<UserDetails> users = List.of(new User("john", "123456", "read"));
        return new InMemoryUserDetailsService(users);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
