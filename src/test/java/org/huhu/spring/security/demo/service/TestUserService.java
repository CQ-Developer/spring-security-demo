package org.huhu.spring.security.demo.service;

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

@WebMvcTest
@Import(AppWebSecurityConfig.class)
class TestUserService {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("使用一个非法的凭据进行身份验证")
    void test_1() throws Exception {
        RequestPostProcessor requestPostProcessor = SecurityMockMvcRequestPostProcessors
                .httpBasic("rose", "123");
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/hello")
                .with(requestPostProcessor);

        ResultMatcher statusMatcher = MockMvcResultMatchers
                .status()
                .isUnauthorized();

        ResultHandler resultHandler = MockMvcResultHandlers
                .print();

        mockMvc.perform(requestBuilder)
                .andExpect(statusMatcher)
                .andDo(resultHandler);
    }

    @Test
    @DisplayName("使用一个合法的凭证进行身份验证")
    void test_2() throws Exception {
        RequestPostProcessor requestPostProcessor = SecurityMockMvcRequestPostProcessors
                .httpBasic("jack", "123");
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/hello")
                .with(requestPostProcessor);

        ResultMatcher statusMatcher = MockMvcResultMatchers
                .status()
                .isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers
                .content()
                .string("Hello!");

        ResultHandler resultHandler = MockMvcResultHandlers
                .print();

        mockMvc.perform(requestBuilder)
                .andExpect(statusMatcher)
                .andExpect(contentMatcher)
                .andDo(resultHandler);
    }

}
