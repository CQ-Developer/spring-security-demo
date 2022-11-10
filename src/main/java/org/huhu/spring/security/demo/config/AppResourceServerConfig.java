package org.huhu.spring.security.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.spel.spi.EvaluationContextExtension;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.jwk.JwkTokenStore;

import static org.springframework.http.HttpMethod.DELETE;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        String aud = "fitnessapp";
        String jwkSetUrl = "http://localhost:8080/realms/master/protocol/openid-connect/certs";
        TokenStore tokenStore = new JwkTokenStore(jwkSetUrl);
        resources.tokenStore(tokenStore).resourceId(aud);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2/**").permitAll()
                .mvcMatchers(DELETE, "/**").hasAnyAuthority("fitnessadmin")
                .anyRequest().authenticated();

        http.csrf().disable();

        http.headers().frameOptions().sameOrigin();
    }

    @Bean
    public EvaluationContextExtension evaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }

}
