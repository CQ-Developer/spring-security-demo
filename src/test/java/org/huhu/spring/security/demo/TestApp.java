package org.huhu.spring.security.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * 测试类
 */
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

        ResultHandler resultHandler = MockMvcResultHandlers.log();

        mockMvc.perform(requestBuilder)
                .andExpect(resultMatcher)
                .andDo(resultHandler);
    }

    /**
     * 当使用 {@link WithMockUser} 时将跳过整个认证过程,
     * 而直接进入授权过程.
     *
     * <p>需要注意的是, 授权过程是发生在认证过程之后的.
     */
    @Test
    @WithMockUser
    @DisplayName("调用/hello端点并提供一个用户")
    void test_2() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/hello");

        ResultMatcher resultMatcherStatus = MockMvcResultMatchers
                .status()
                .isOk();
        ResultMatcher resultMatcherContent = MockMvcResultMatchers
                .content()
                .string("Hello!");

        ResultHandler resultHandler = MockMvcResultHandlers.log();

        mockMvc.perform(requestBuilder)
                .andExpectAll(resultMatcherStatus)
                .andExpect(resultMatcherContent)
                .andDo(resultHandler);
    }

}
