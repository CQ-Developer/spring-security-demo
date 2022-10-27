package org.huhu.spring.security.demo.conf;

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
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;

import static org.springframework.http.HttpMethod.GET;

@Configuration
public class AppSecurityConfiguration {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails john = User.withUsername("john")
                               .password("123")
                               .roles("ADMIN")
                               .build();
        UserDetails jane = User.withUsername("Jane")
                               .password("123")
                               .roles("MANAGER")
                               .build();
        return new InMemoryUserDetailsManager(john, jane);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 使用 {@link MvcRequestMatcher} 匹配指定的访问路径.
     *
     * @see MvcRequestMatcher
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();
        // 禁用CSRF已方便演示
        httpSecurity.csrf()
                    .disable();
        httpSecurity.authorizeRequests()
                    .mvcMatchers(GET, "/a/**").authenticated()
                    .anyRequest().denyAll();
        return httpSecurity.build();
    }

}
