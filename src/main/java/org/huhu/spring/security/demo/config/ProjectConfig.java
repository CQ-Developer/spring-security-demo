package org.huhu.spring.security.demo.config;

import org.huhu.spring.security.demo.model.User;
import org.huhu.spring.security.demo.service.InMemoryUserDetailsService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

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
    public UserDetailsService userDetailsService(DataSource dataSource) {
        // 使用数据库访问用户信息
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

        // 自定义查询
        String usersByUsernameQuery = "select username, password, enabled from users where username = ?";
        userDetailsManager.setUsersByUsernameQuery(usersByUsernameQuery);

        // 自定义查询
        String authoritiesByUsernameQuery = "select username, authority from authorities where username = ?";
        userDetailsManager.setAuthoritiesByUsernameQuery(authoritiesByUsernameQuery);

        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 该配置仅允许访问H2数据库控制台
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic()
                .and()
                .csrf().ignoringAntMatchers("/h2/**")
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .build();
    }

}
