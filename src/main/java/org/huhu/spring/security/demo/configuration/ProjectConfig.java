package org.huhu.spring.security.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        // 配置所有请求都要进行身份验证
        http.authorizeRequests().anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 创建用户
        UserDetails user = User.withUsername("john")
                .password("123456")
                .authorities("read")
                .build();

        // 创建并配置 UserDetailsService
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
        userDetailsService.createUser(user);

        // 创建 PasswordEncoder
        PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();

        // 将 UserDetailsService 和 PasswordEncoder 配置到 AuthenticationManagerBuilder 中
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

}
