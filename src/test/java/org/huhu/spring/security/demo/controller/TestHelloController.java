package org.huhu.spring.security.demo.controller;

import org.huhu.spring.security.demo.config.AppWebSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClientConfigurer;

@WebFluxTest(controllers = HelloController.class)
@Import(AppWebSecurityConfig.class)
class TestHelloController {

    @Autowired
    WebTestClient webTestClient;

    @Test
    @DisplayName("调用/hello端点-不使用凭证")
    void test_1() throws Exception {
        webTestClient.get()
                .uri("/hello")
                .exchange()
                .expectStatus()
                .isUnauthorized();
    }

    @Test
    @WithMockUser
    @DisplayName("调用/hello端点-模拟用户")
    void test_2() throws Exception {
        webTestClient.get()
                .uri("/hello")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .isEqualTo("Hello!");
    }

    @Test
    @WithUserDetails("jack")
    @DisplayName("调用/hello端点-实际用户")
    void test_3() throws Exception {
        webTestClient.get()
                .uri("/hello")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .isEqualTo("Hello!");
    }

    @Test
    @DisplayName("调用/hello端点-模拟用户")
    void test_4() throws Exception {
        WebTestClientConfigurer webTestClientConfigurer = SecurityMockServerConfigurers
                .mockUser();
        webTestClient.mutateWith(webTestClientConfigurer)
                .get()
                .uri("/hello")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .isEqualTo("Hello!");
    }

}
