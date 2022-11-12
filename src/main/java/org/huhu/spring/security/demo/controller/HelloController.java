package org.huhu.spring.security.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String getHello() {
        return "get hello";
    }

    @PostMapping("/hello")
    public String postHello() {
        return "post hello";
    }

}
