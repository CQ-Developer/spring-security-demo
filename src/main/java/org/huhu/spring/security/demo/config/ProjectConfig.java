package org.huhu.spring.security.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();
        // 只有ADMIN角色才能访问/hello端点
        // 只有MANAGER角色才能访问/ciao端点
        // 其余端点任何人都能访问(包括未认证过的用户)
        httpSecurity.authorizeRequests()
                    .mvcMatchers("/hello").hasRole("ADMIN")
                    .mvcMatchers("/ciao").hasRole("MANAGER")
                    .anyRequest().permitAll();
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // ROLE_ADMIN: read, write, update, delete
        UserDetails john = User.withUsername("john")
                               .password("123")
                               .roles("ADMIN")
                               .build();
        // ROLE_MANAGER: read, write
        UserDetails jane = User.withUsername("jane")
                               .password("123")
                               .roles("MANAGER")
                               .build();
        return new InMemoryUserDetailsManager(john, jane);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
