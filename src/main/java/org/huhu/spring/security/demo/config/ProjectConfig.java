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

    private static final String AUTHORITY_READ = "READ";
    private static final String AUTHORITY_WRITE = "WRITE";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();
        httpSecurity.authorizeRequests()
                    .anyRequest()
                    .hasAuthority(AUTHORITY_WRITE);
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails john = User.withUsername("john")
                               .password("123")
                               .authorities(AUTHORITY_READ)
                               .build();
        UserDetails jane = User.withUsername("jane")
                               .password("123")
                               .authorities(AUTHORITY_WRITE)
                               .build();
        return new InMemoryUserDetailsManager(john, jane);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
