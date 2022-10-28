package org.huhu.spring.security.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/")
    public String main() {
        return "main.html";
    }

    @ResponseBody
    @PostMapping("/test")
    @CrossOrigin(origins = {"http://localhost:8080"})
    public String test() {
        logger.info("Test method called");
        return "HELLO";
    }

}
