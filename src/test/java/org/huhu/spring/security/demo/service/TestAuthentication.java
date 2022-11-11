package org.huhu.spring.security.demo.service;

import org.huhu.spring.security.demo.config.AppWebSecurityConfig;
import org.huhu.spring.security.demo.config.AppWebSecurityTesterConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
@Import({AppWebSecurityTesterConfig.class, AppWebSecurityConfig.class})
class TestAuthentication {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("使用一个非法的凭证进行认证")
    void test_1() throws Exception {
        RequestBuilder requestBuilder = SecurityMockMvcRequestBuilders
                .formLogin()
                .user("bill")
                .password("123");
        ResultMatcher unauthenticatedMatcher = SecurityMockMvcResultMatchers
                .unauthenticated();
        ResultMatcher headerMatcher = MockMvcResultMatchers
                .header()
                .exists("fail");
        ResultHandler resultHandler = MockMvcResultHandlers.print();
        mockMvc.perform(requestBuilder)
                .andExpect(unauthenticatedMatcher)
                .andExpect(headerMatcher)
                .andDo(resultHandler);
    }

    @Test
    @DisplayName("使用一个未授权的凭证进行认证")
    void test_2() throws Exception {
        RequestBuilder requestBuilder = SecurityMockMvcRequestBuilders
                .formLogin()
                .user("rose")
                .password("123");
        ResultMatcher authenticatedMatcher = SecurityMockMvcResultMatchers
                .authenticated();
        ResultMatcher statusMatcher = MockMvcResultMatchers
                .status()
                .isFound();
        ResultMatcher redirectMatcher = MockMvcResultMatchers
                .redirectedUrl("/error");
        ResultHandler resultHandler = MockMvcResultHandlers
                .print();
        mockMvc.perform(requestBuilder)
                .andExpect(authenticatedMatcher)
                .andExpect(statusMatcher)
                .andExpect(redirectMatcher)
                .andDo(resultHandler);
    }

    @Test
    @DisplayName("使用一个已授权的凭证进行认证")
    void test_3() throws Exception {
        RequestBuilder requestBuilder = SecurityMockMvcRequestBuilders
                .formLogin()
                .user("jack")
                .password("123");
        ResultMatcher authenticatedMatcher = SecurityMockMvcResultMatchers
                .authenticated();
        ResultMatcher statusMatcher = MockMvcResultMatchers
                .status()
                .isFound();
        ResultMatcher redirectMatcher = MockMvcResultMatchers
                .redirectedUrl("/home");
        ResultHandler resultHandler = MockMvcResultHandlers
                .print();
        mockMvc.perform(requestBuilder)
                .andExpect(authenticatedMatcher)
                .andExpect(statusMatcher)
                .andExpect(redirectMatcher)
                .andDo(resultHandler);
    }

}
