package org.huhu.spring.security.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String getHello() {
        return "GET Hello!";
    }

    @PostMapping("/hello")
    public String postHello() {
        return "POST Hello!";
    }

    @PostMapping("/ciao")
    public String postCiao() {
        return "POST Ciao";
    }

}
