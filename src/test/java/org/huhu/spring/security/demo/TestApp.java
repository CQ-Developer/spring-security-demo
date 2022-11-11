package org.huhu.spring.security.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
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
class TestApp {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("调用/hello端点但不提供任何用户")
    void test_1() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/hello");

        ResultMatcher resultMatcher = MockMvcResultMatchers
                .status()
                .isUnauthorized();

        ResultHandler resultHandler = MockMvcResultHandlers.print();

        mockMvc.perform(requestBuilder)
                .andExpect(resultMatcher)
                .andDo(resultHandler);
    }

    /**
     * 当使用 {@link WithMockUser} 时可以指定摸一个具体的用户.
     */
    @Test
    @WithMockUser(username = "jack")
    @DisplayName("调用/hello端点并提供一个具体的用户")
    void test_2() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/hello");

        ResultMatcher resultMatcherStatus = MockMvcResultMatchers
                .status()
                .isOk();
        ResultMatcher resultMatcherContent = MockMvcResultMatchers
                .content()
                .string("Hello jack");

        ResultHandler resultHandler = MockMvcResultHandlers.print();

        mockMvc.perform(requestBuilder)
                .andExpectAll(resultMatcherStatus)
                .andExpect(resultMatcherContent)
                .andDo(resultHandler);
    }

    /**
     * 使用 {@link RequestPostProcessor} 和 {@link WithMockUser} 的主要区别是,
     * {@link RequestPostProcessor} 是在创建完测试请求后, 再构建安全环境.
     * 而 {@link WithMockUser} 会首先构建测试安全环境, 再创建测试请求.
     */
    @Test
    @DisplayName("调用/hello端点并通过RequestPostProcessor提供一个用户")
    void test_3() throws Exception {
        RequestPostProcessor requestPostProcessor = SecurityMockMvcRequestPostProcessors
                .user("rose");
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/hello")
                .with(requestPostProcessor);

        ResultMatcher resultMatcherStatus = MockMvcResultMatchers
                .status()
                .isOk();
        ResultMatcher resultMatcherContent = MockMvcResultMatchers
                .content()
                .string("Hello rose");

        ResultHandler resultHandler = MockMvcResultHandlers.print();

        mockMvc.perform(requestBuilder)
                .andExpectAll(resultMatcherStatus)
                .andExpect(resultMatcherContent)
                .andDo(resultHandler);
    }

}
