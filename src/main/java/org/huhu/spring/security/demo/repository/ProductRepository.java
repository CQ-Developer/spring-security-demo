package org.huhu.spring.security.demo.repository;

import org.huhu.spring.security.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostFilter;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    /**
     * 并不推荐这种做法.
     * 因为会先查询数据库中的所有数据再根据授权条件过滤结果集.
     * 注意也可以使用 {@code filterObject.owner == authentication.principal.username}
     */
    @PostFilter("filterObject.owner == authentication.name")
    List<Product> findProductByNameContains(String text);

}
