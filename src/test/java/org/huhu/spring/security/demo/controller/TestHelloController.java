package org.huhu.spring.security.demo.controller;

import org.huhu.spring.security.demo.config.AppWebSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * 如果使用 {@link SpringBootTest} 注解, 则无需使用 {@link Import} 导入安全的配置类.
 * 因为测试环境会自动装配一个安全上下文, 但 {@link WebMvcTest} 仅会启用 Web 相关的组件,
 * 不会启动项目中 SpringSecurity 相关的配置.
 */
@Import(AppWebSecurityConfig.class)
@WebMvcTest(controllers = HelloController.class)
class TestHelloController {

    @Autowired
    MockMvc mockMvc;

    /**
     * 使用 {@link WithUserDetails} 获取一个已存在的用户,
     * 不同于使用 {@link WithMockUser} 模拟一个不存在的用户.
     */
    @Test
    @WithUserDetails("rose")
    @DisplayName("使用一个已存在的用户测试认证流程")
    void test() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/hello");
        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().string("Hello rose");
        ResultHandler resultHandler = MockMvcResultHandlers.print();
        mockMvc.perform(requestBuilder)
                .andExpect(statusMatcher)
                .andExpect(contentMatcher)
                .andDo(resultHandler);
    }

}
