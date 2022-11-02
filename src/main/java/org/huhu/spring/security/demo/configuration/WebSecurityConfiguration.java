package org.huhu.spring.security.demo.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * 为授权码授权类型的用户提供一个认证的界面.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
    }

    /**
     * 将 {@link AuthenticationManager} 作为Bean暴露给 {@link ApplicationContext}.
     * 以便在授权服务器中使用.
     *
     * @see Oauth2AuthorizationServerConfiguration
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 在授权服务器授权前, 用户需要向授权服务进行认证.
     * 这里预制一个用户方便登录使用.
     */
    @Bean
    public UserDetailsService customizeUserDetailsService() {
        UserDetails john = User.withUsername("john")
                               .password("123")
                               .authorities("read")
                               .build();
        return new InMemoryUserDetailsManager(john);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
