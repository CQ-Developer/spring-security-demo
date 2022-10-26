package org.huhu.spring.security.demo.repository;

import org.huhu.spring.security.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {}
