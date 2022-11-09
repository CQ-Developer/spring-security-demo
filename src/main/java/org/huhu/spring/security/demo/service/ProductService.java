package org.huhu.spring.security.demo.service;

import org.huhu.spring.security.demo.config.AppSecurityConfiguration;
import org.huhu.spring.security.demo.entity.Product;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProductService {

    /**
     * <p>与 {@link PostAuthorize} 相同,
     * 后过滤需要通过 {@link EnableGlobalMethodSecurity#prePostEnabled} 开启.
     *
     * <p>通过在方法上定义 {@link PostFilter} 注解实现后过滤.
     *
     * <p>在 {@link PostFilter} 中通过 {@code filterObject} 可以引用集合返回值中的元素.
     *
     * <p>在 {@link PostFilter} 中通过 {@code authentication} 可以引用存储在 {@link SecurityContext}
     * 中的 {@link Authentication} 对象.
     *
     * <p>同 {@link PreFilter} 只适用于集合或数组类型的参数,
     * {@link PostFilter} 只适用于集合或数组类型的返回值.
     *
     * @see AppSecurityConfiguration
     */
    @PostFilter("filterObject.owner == authentication.name")
    public List<Product> sellProducts() {
        // 模拟数据库查询结果
        List<Product> products = new LinkedList<>();
        products.add(new Product("beer", "jack"));
        products.add(new Product("candy", "jack"));
        products.add(new Product("chocolate", "rose"));

        return products;
    }

}
