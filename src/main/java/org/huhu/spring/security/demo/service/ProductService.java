package org.huhu.spring.security.demo.service;

import org.huhu.spring.security.demo.config.AppSecurityConfiguration;
import org.huhu.spring.security.demo.entity.Product;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    /**
     * <p>与 {@link PreAuthorize} 相同,
     * 预过滤需要通过 {@link EnableGlobalMethodSecurity#prePostEnabled} 开启.
     *
     * <p>通过在方法上定义 {@link PreFilter} 注解实现预过滤.
     *
     * <p>在 {@link PreFilter} 中通过 {@code filterObject} 可以引用集合参数中的元素.
     *
     * <p>在 {@link PreFilter} 中通过 {@code authentication} 可以引用存储在 {@link SecurityContext}
     * 中的 {@link Authentication} 对象.
     *
     * <p>注意预过滤和后过滤只适用于数组或集合.
     *
     * @see AppSecurityConfiguration
     */
    @PreFilter("filterObject.owner == authentication.name")
    public List<Product> sellProducts(List<Product> products) {
        return products;
    }

}
