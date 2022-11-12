package org.huhu.spring.security.demo.controller;

import org.huhu.spring.security.demo.config.AppWebSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN;
import static org.springframework.http.HttpHeaders.ALLOW;
import static org.springframework.http.HttpHeaders.ORIGIN;
import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.web.cors.CorsConfiguration.ALL;

@WebMvcTest(controllers = HelloController.class)
@Import(AppWebSecurityConfig.class)
class TestHelloController {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("调用/test端点测试CORS")
    void test() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .options("/test")
                .header(ACCESS_CONTROL_ALLOW_METHODS, POST.name())
                .header(ORIGIN, "http://127.0.0.1:8080");
        ResultMatcher statusMatcher = MockMvcResultMatchers
                .status()
                .isOk();
        ResultMatcher originExistMatcher = MockMvcResultMatchers
                .header()
                .exists(ACCESS_CONTROL_ALLOW_ORIGIN);
        ResultMatcher originValueMatcher = MockMvcResultMatchers
                .header()
                .string(ACCESS_CONTROL_ALLOW_ORIGIN, ALL);
        ResultMatcher methodExistMatcher = MockMvcResultMatchers
                .header()
                .exists(ALLOW);
        ResultMatcher methodValueMatcher = MockMvcResultMatchers
                .header()
                .string(ALLOW, POST.name() + "," + OPTIONS.name());
        ResultHandler resultHandler = MockMvcResultHandlers
                .print();
        mockMvc.perform(requestBuilder)
                .andExpect(statusMatcher)
                .andExpect(originExistMatcher)
                .andExpect(originValueMatcher)
                .andExpect(methodExistMatcher)
                .andExpect(methodValueMatcher)
                .andDo(resultHandler);
    }

}
