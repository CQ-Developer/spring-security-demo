package org.huhu.spring.security.demo.user.details;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

class Demo {

    /**
     * 使用 Spring 提供的工具类 {@link User} 创建一个不可变的 {@link UserDetails} 实例
     */
    void demo1() {
        UserDetails userDetails = User.withUsername("bill")
                .password("123456")
                .authorities("READ")
                .accountExpired(false)
                .disabled(true)
                .build();
        System.out.println(userDetails);
    }

    /**
     * 也可以从一个已经存在的 {@link UserDetails} 实例进行构造
     */
    void demo2() {
        // 一个已存在的UserDetails
        UserDetails source = User
                .withUsername("bill")
                .password("123456")
                .authorities("READ")
                // 该方法进行定义了一个passwordEncoder对密码进行编码
                // 并不是SpringSecurity提供的PasswordEncoder接口
                .passwordEncoder(p -> "encoded-" + p)
                .accountExpired(false)
                .disabled(true)
                .build();
        // 生成一个新的UserDetails
        UserDetails target = User.withUserDetails(source).build();
        System.out.println(target);
    }

}
