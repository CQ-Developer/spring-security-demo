package org.huhu.spring.security.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(Authentication authentication) {
        // 通过Spring直接注入当前已认证的用户
        return "Hello, " + authentication.getName() + "!";
    }

}
