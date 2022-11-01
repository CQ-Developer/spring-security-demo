package org.huhu.spring.security.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;

import java.util.List;
import java.util.Map;

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
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        BaseClientDetails clientDetails = new BaseClientDetails();
        clientDetails.setClientId("client");
        clientDetails.setClientSecret("secret");
        clientDetails.setScope(List.of("read"));
        clientDetails.setAuthorizedGrantTypes(List.of("password"));

        InMemoryClientDetailsService clientDetailsService = new InMemoryClientDetailsService();
        clientDetailsService.setClientDetailsStore(Map.of("client", clientDetails));

        clients.withClientDetails(clientDetailsService);
    }

}
