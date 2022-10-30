package org.huhu.spring.security.demo.service;

import org.huhu.spring.security.demo.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationServerProxy {

    public final RestTemplate restTemplate;

    private final String baseUrl = "http://localhost:7070";

    public AuthenticationServerProxy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendAuth(String username, String password) {
        String url = baseUrl + "/user/auth";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        restTemplate.postForEntity(url, new HttpEntity<>(user), Void.class);
    }

    public boolean sendOTP(String username, String code) {
        String url = baseUrl + "/otp/check";
        User user = new User();
        user.setUsername(username);
        user.setCode(code);
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(url, new HttpEntity<>(user), Void.class);
        return responseEntity.getStatusCode().is2xxSuccessful();
    }

}
