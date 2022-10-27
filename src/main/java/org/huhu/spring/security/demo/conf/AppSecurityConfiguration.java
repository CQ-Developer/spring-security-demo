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
     * 匹配路径参数, 只有参数值匹配正则表达式时, 次路径的规则才应用.
     * {@code ^[0-9]*$} 当路径参数为数字时, 可以随便访问.
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
                    .mvcMatchers("/product/{code:^[0-9]*$}").permitAll()
                    .anyRequest().denyAll();
        return httpSecurity.build();
    }

}
