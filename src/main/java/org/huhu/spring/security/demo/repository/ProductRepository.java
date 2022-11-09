package org.huhu.spring.security.demo.repository;

import org.huhu.spring.security.demo.config.AppSecurityConfigurer;
import org.huhu.spring.security.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    /**
     * 在 Spring Data 的查询语句中直接使用 Spring Security 提供的 SpEL.
     *
     * @see AppSecurityConfigurer#securityEvaluationContextExtension
     */
    @Query("SELECT p FROM t_product p WHERE p.name LIKE %:text% AND p.owner = ?#{authentication.name}")
    List<Product> findProductByNameContains(String text);

}
