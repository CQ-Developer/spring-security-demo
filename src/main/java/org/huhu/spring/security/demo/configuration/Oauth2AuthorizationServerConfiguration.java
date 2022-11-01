package org.huhu.spring.security.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * 通过注解 {@link EnableAuthorizationServer} 指示SpringBoot启用OAuth2授权服务器的配置.
 * 通过继承 {@link AuthorizationServerConfigurerAdapter} 对OAuth2授权服务器进行自定义配置.
 */
@Configuration
@EnableAuthorizationServer
public class Oauth2AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    /**
     * 通过SpringSecurity配置类暴露的Bean.
     *
     * @see WebSecurityConfiguration#authenticationManagerBean
     */
    private final AuthenticationManager authenticationManager;

    public Oauth2AuthorizationServerConfiguration(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }

}
