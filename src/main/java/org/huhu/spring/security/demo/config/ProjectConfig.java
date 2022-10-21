package org.huhu.spring.security.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.DefaultLdapUsernameToDnMapper;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.userdetails.LdapUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

/**
 * 一个简单的 LDAP 实现的 {@link UserDetailsManager}
 *
 * @see LdapUserDetailsManager
 */
@Configuration
public class ProjectConfig {

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        // 创建上下文源指定LDAP服务器地址
        DefaultSpringSecurityContextSource contextSource = new DefaultSpringSecurityContextSource("ldap://localhost:33389/dc=springframework,dc=org");
        contextSource.afterPropertiesSet();

        // 创建LdapUserDetailsManager的实例
        LdapUserDetailsManager userDetailsManager = new LdapUserDetailsManager(contextSource);
        // 设置用户名映射器指定LdapUserDetailsManager如何搜索用户
        userDetailsManager.setUsernameMapper(new DefaultLdapUsernameToDnMapper("ou=groups", "uid"));
        // 设置应用程序搜索用户所需的群组搜索库
        userDetailsManager.setGroupSearchBase("ou=groups");

        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
