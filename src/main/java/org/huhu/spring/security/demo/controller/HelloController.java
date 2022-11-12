package org.huhu.spring.security.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @PostMapping("/test")
    public String test() {
        logger.info("Test method called");
        return "HELLO";
    }

}
