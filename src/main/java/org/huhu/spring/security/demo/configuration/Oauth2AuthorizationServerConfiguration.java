package org.huhu.spring.security.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;

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

    /**
     * OAuth2授权服务器通过 {@link AuthenticationManager} 管理用户.
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }

    /**
     * OAuth2授权服务器通过 {@link ClientDetailsService} 管理客户端.
     * 使用授权码类型的授权.
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
               .withClient("client1")
               .secret("secret")
               .authorizedGrantTypes("authorization_code")
               .scopes("read")
               .redirectUris("http://localhost:9090/home")
               .and()
               // 为client2用户配置多种认证方式
               // 这种方式并不推荐
               .inMemory()
               .withClient("client2")
               .secret("secret")
               .authorizedGrantTypes("authorization_code", "password", "refresh_token")
               .scopes("read")
               .redirectUris("http://localhost:9090/home");
    }

}
