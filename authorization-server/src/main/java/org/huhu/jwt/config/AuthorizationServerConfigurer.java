package org.huhu.jwt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.time.ZoneId;
import java.util.List;
import java.util.Map;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    private final JwtAccessTokenConverter jwtAccessTokenConverter;
    private final TokenStore tokenStore;

    public AuthorizationServerConfigurer(AuthenticationManager authenticationManager,
            JwtAccessTokenConverter jwtAccessTokenConverter, JwtTokenStore tokenStore) {
        this.authenticationManager = authenticationManager;
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
        this.tokenStore = tokenStore;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(List.of(tokenEnhancer(), jwtAccessTokenConverter));

        endpoints.authenticationManager(authenticationManager)
                 .tokenStore(tokenStore)
                 .tokenEnhancer(tokenEnhancerChain);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
               .withClient("client")
               .secret("secret")
               .authorizedGrantTypes("password", "refresh_token")
               .scopes("read");
    }

    private TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            Map<String, Object> zoneInfo = Map.of("generatedZoneInfo", ZoneId.systemDefault().toString());

            DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
            defaultOAuth2AccessToken.setAdditionalInformation(zoneInfo);

            return defaultOAuth2AccessToken;
        };
    }

}
