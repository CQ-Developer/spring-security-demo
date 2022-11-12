package org.huhu.spring.security.demo.controller;

import org.huhu.spring.security.demo.config.AppWebSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = HelloController.class)
@Import(AppWebSecurityConfig.class)
class TestHelloController {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("使用GET方法调用/hello端点")
    void test_1() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/hello");
        ResultMatcher statusMatcher = MockMvcResultMatchers
                .status()
                .isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers
                .content()
                .string("get hello");
        ResultHandler resultHandler = MockMvcResultHandlers
                .print();
        mockMvc.perform(requestBuilder)
                .andExpect(statusMatcher)
                .andExpect(contentMatcher)
                .andDo(resultHandler);
    }

    @Test
    @DisplayName("使用POST方法调用/hello端点")
    void test_2() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/hello");
        ResultMatcher statusMatcher = MockMvcResultMatchers
                .status()
                .isForbidden();
        ResultHandler resultHandler = MockMvcResultHandlers
                .print();
        mockMvc.perform(requestBuilder)
                .andExpect(statusMatcher)
                .andDo(resultHandler);
    }

    @Test
    @DisplayName("使用POST方法调用/hello端点并提供CsrfToken")
    void test_3() throws Exception {
        RequestPostProcessor requestPostProcessor = SecurityMockMvcRequestPostProcessors
                .csrf();
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/hello")
                .with(requestPostProcessor);
        ResultMatcher statusMatcher = MockMvcResultMatchers
                .status()
                .isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers
                .content()
                .string("post hello");
        ResultHandler resultHandler = MockMvcResultHandlers
                .print();
        mockMvc.perform(requestBuilder)
                .andExpect(statusMatcher)
                .andExpect(contentMatcher)
                .andDo(resultHandler);
    }

}
