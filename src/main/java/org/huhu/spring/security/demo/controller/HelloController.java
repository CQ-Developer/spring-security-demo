package org.huhu.spring.security.demo.controller;

import org.huhu.spring.security.demo.config.ProjectConfig;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(Authentication authentication) {
        // 通过Spring直接注入当前已认证的用户
        return "Hello, " + authentication.getName() + "!";
    }

    /**
     * 通过设置 {@link SecurityContextHolderStrategy} 让异步方法也能获得 {@link SecurityContext}
     *
     * @see ProjectConfig#initializingBean()
     */
    @Async
    @GetMapping("/bye")
    public void goodbye() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        System.out.println(authentication.getName());
    }

}
