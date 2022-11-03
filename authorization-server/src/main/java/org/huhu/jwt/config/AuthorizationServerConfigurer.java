package org.huhu.jwt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    private final String jwtKey = "ymLTU8rq83j4fmJZj60wh4OrMNuntIj4fmJ";

    private final AuthenticationManager authenticationManager;

    public AuthorizationServerConfigurer(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 为JWT添加签名算法的密钥
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(jwtKey);

        // 启用JWT
        JwtTokenStore jwtTokenStore = new JwtTokenStore(jwtAccessTokenConverter);

        endpoints.authenticationManager(authenticationManager)
                 .tokenStore(jwtTokenStore)
                 .accessTokenConverter(jwtAccessTokenConverter);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
               .withClient("client")
               .secret("secret")
               .authorizedGrantTypes("password", "refresh_token")
               .scopes("read");
    }
}
