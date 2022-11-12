package org.huhu.spring.security.demo.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = HelloController.class)
class TestHelloController {

    @Autowired
    WebTestClient webTestClient;

    @Test
    @DisplayName("测试webflux程序")
    void test() throws Exception {
        webTestClient.get()
                .uri("/hello")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .isEqualTo("Hello!");
    }

}
