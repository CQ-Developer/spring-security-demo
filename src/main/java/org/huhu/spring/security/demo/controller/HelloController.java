package org.huhu.spring.security.demo.controller;

import org.huhu.spring.security.demo.entity.Employee;
import org.huhu.spring.security.demo.service.NameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final NameService nameService;

    public HelloController(NameService nameService) {
        this.nameService = nameService;
    }

    @GetMapping("/book/details/{name}")
    public Employee hello(@PathVariable String name) {
        return nameService.getSecretName(name);
    }

}
