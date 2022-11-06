package org.huhu.spring.security.demo.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NameService {

    private final Map<String, List<String>> secretNames = Map.of(
            "chen", List.of("father", "strong"),
            "ruan", List.of("mother", "kind"));

    /**
     * 通过 {@code #name} 引用方法参数.
     * 并且可以直接访问身份验证对象, 可以使用该对象引用当前经过身份验证的用户.
     */
    @PreAuthorize("#name == authentication.principal.username")
    public List<String> getSecretName(String name) {
        return secretNames.get(name);
    }

}
