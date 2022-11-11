package org.huhu.spring.security.demo.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@TestConfiguration
public class AppWebSecurityTesterConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails jack = User.withUsername("jack")
                .password("123")
                .authorities("read")
                .build();
        UserDetails rose = User.withUsername("rose")
                .password("123")
                .authorities("write")
                .build();
        return new InMemoryUserDetailsManager(jack, rose);
    }

}
