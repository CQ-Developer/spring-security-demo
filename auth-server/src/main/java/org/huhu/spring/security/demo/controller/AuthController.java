package org.huhu.spring.security.demo.controller;

import org.huhu.spring.security.demo.entity.otp;
import org.huhu.spring.security.demo.entity.User;
import org.huhu.spring.security.demo.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static javax.servlet.http.HttpServletResponse.SC_OK;

@RestController
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/add")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @PostMapping("/user/auth")
    public void auth(@RequestBody User user) {
        userService.auth(user);
    }

    @PostMapping("/otp/check")
    public void check(@RequestBody otp otp, HttpServletResponse httpResponse) {
        boolean checked = userService.check(otp);
        if (checked) {
            httpResponse.setStatus(SC_OK);
        } else {
            httpResponse.setStatus(SC_FORBIDDEN);
        }
    }

}
