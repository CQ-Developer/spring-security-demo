package org.huhu.spring.security.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * <p>当前版本中不需要继承 {@link GlobalMethodSecurityConfiguration},
 * 因为 {@link EnableGlobalMethodSecurity} 注解会将当前类自动标注为 {@link Configuration} 类,
 * 并且会在 {@link ImportSelector} 中自动装配 {@link GlobalMethodSecurityConfiguration}.
 *
 * <p>如果需要对 {@link GlobalMethodSecurityConfiguration} 进行一些自定义的配置,
 * 可以选择继承 {@link GlobalMethodSecurityConfiguration} 并重写需要自定义的方法.
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ProjectConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails chen = User.withUsername("chen")
                .password("123")
                .roles("ADMIN")
                .build();
        UserDetails huhu = User.withUsername("huhu")
                .password("123")
                .roles("MANAGER")
                .build();
        return new InMemoryUserDetailsManager(chen, huhu);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
