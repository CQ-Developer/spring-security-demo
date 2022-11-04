package org.huhu.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import static java.nio.charset.StandardCharsets.UTF_8;

@Configuration
public class ResourceServerWebSecurityConfigurer {

    private final String jwtKey = "ymLTU8rq83j4fmJZj60wh4OrMNuntIj4fmJ";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                    .anyRequest().authenticated();

        SecretKey secretKey = new SecretKeySpec(jwtKey.getBytes(UTF_8), "HMACSHA256");
        JwtDecoder jwtDecoder = NimbusJwtDecoder.withSecretKey(secretKey).build();
        httpSecurity.oauth2ResourceServer()
                    .jwt()
                    .decoder(jwtDecoder);

        return httpSecurity.build();
    }

}
