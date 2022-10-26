package org.huhu.spring.security.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/a")
    public String getA() {
        return "Works!";
    }

    @PostMapping("/a")
    public String postA() {
        return "Works!";
    }

    @GetMapping("/a/b")
    public String getAB() {
        return "Works!";
    }

    @GetMapping("/a/b/c")
    public String getABC() {
        return "Works!";
    }

}
