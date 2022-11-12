package org.huhu.spring.security.demo.controller;

import org.huhu.spring.security.demo.config.AppWebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = HelloController.class)
@Import(AppWebSecurityConfig.class)
class TestHelloController {

    @Autowired
    MockMvc mockMvc;

}
