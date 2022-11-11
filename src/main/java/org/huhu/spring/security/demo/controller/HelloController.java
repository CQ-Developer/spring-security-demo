package org.huhu.spring.security.demo.controller;

import org.huhu.spring.security.demo.config.AppSecurityConfig;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {

    /**
     * 通过 {@link PreAuthorize} 注解定义授权规则.
     * 需要通过 {@link EnableReactiveMethodSecurity} 开启反应式方法安全.
     *
     * @see AppSecurityConfig
     */
    @GetMapping("/hello")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<String> hello(Mono<Authentication> authentication) {
        return authentication.map(a -> "Hello " + a.getName());
    }

}
