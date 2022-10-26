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

    private static final String AUTHORITY_DELETE = "DELETE";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();
        // 只有具备READ权限且不具备DELETE权限的用户才能访问
        httpSecurity.authorizeRequests()
                    .anyRequest()
                    .access("hasAuthority('READ') and !hasAuthority('DELETE')");
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
                               .authorities(AUTHORITY_READ, AUTHORITY_WRITE, AUTHORITY_DELETE)
                               .build();
        return new InMemoryUserDetailsManager(john, jane);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
