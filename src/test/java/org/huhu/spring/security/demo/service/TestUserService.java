package org.huhu.spring.security.demo.service;

import org.huhu.spring.security.demo.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
class TestUserService {

    @Autowired
    UserService userService;

    @Test
    @DisplayName("不是使用任何用户调用预授权方法")
    void test_1() {
        Assertions.assertThrows(AuthenticationException.class, () -> userService.getName());
    }

    @Test
    @DisplayName("使用一个不具备权限的用户调用预授权方法")
    @WithMockUser(authorities = "read")
    void test_2() {
        Assertions.assertThrows(AccessDeniedException.class, () -> userService.getName());
    }

    @Test
    @DisplayName("使用一个具备权限的用户调用预授权方法")
    @WithMockUser(authorities = "write")
    void test_3() {
        Assertions.assertEquals("Jack", userService.getName());
    }

}
