package org.huhu.spring.security.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/")
    public String main(OAuth2AuthenticationToken authentication) {
        logger.info("obtain OAuth2User from OAuth2AuthenticationToken: {}", authentication.getPrincipal());
        return "main.html";
    }

}
