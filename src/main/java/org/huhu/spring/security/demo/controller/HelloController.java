package org.huhu.spring.security.demo.controller;

import org.huhu.spring.security.demo.service.NameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    private final NameService nameService;

    public HelloController(NameService nameService) {
        this.nameService = nameService;
    }

    @GetMapping("/secret/names/{name}")
    public List<String> hello(@PathVariable String name) {
        return nameService.getSecretName(name);
    }

}
