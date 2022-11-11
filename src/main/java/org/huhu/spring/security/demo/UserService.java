package org.huhu.spring.security.demo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @PreAuthorize("hasAuthority('write')")
    public String getName() {
        return "Jack";
    }

}
