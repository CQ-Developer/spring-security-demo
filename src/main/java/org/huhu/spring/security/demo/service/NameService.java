package org.huhu.spring.security.demo.service;

import org.huhu.spring.security.demo.entity.Employee;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NameService {

    private final Map<String, Employee> records = Map.of(
            "zhang",
            new Employee("zhang san",
                    List.of("bookA", "booB"),
                    List.of("reader")),
            "li",
            new Employee("li si",
                    List.of("bookA", "bookC"),
                    List.of("researcher")));

    @PostAuthorize("returnObject.roles.contains('reader')")
    public Employee getSecretName(String name) {
        return records.get(name);
    }

}
