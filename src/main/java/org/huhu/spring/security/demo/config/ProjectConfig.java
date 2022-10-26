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
        // 只有ADMIN角色才能访问
        httpSecurity.authorizeRequests()
                    .anyRequest()
                    .hasRole("ADMIN");
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // ROLE_ADMIN: read, write, update, delete
        UserDetails john = User.withUsername("john")
                               .password("123")
                               .authorities("ROLE_ADMIN")
                               .build();
        // ROLE_MANAGER: read, write
        UserDetails jane = User.withUsername("jane")
                               .password("123")
                               .authorities("ROLE_MANAGER")
                               .build();
        return new InMemoryUserDetailsManager(john, jane);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
