package org.huhu.spring.security.demo.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class NameService {

    /**
     * 定义授权规则:
     * 只有具有write权限的用户才能调用此方法.
     * 可选的方法包括了:
     * hasAnyAuthority()
     * hasRole()
     * hasAnyRole()
     */
    @PreAuthorize("hasAuthority('write')")
    public String getName() {
        return "NanJing";
    }

}
