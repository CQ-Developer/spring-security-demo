package org.huhu.spring.security.demo.controller;

import org.huhu.spring.security.demo.config.WithCustomizedAuthentication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = HelloController.class)
class TestHelloController {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("不使用任何用户方法/hello端点")
    void test_1() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/hello");
        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isUnauthorized();
        ResultHandler resultHandler = MockMvcResultHandlers.print();
        mockMvc.perform(requestBuilder)
                .andExpect(statusMatcher)
                .andDo(resultHandler);
    }

    @Test
    @WithCustomizedAuthentication(username = "jack")
    @DisplayName("使用自定义配置的SecurityContext访问/hello端点")
    void test_2() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/hello");
        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().string("Hello jack");
        ResultHandler resultHandler = MockMvcResultHandlers.print();
        mockMvc.perform(requestBuilder)
                .andExpect(statusMatcher)
                .andExpect(contentMatcher)
                .andDo(resultHandler);
    }

}
