package org.huhu.spring.security.demo.service;

import org.huhu.spring.security.demo.entity.Product;
import org.huhu.spring.security.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll(String text) {
        return productRepository.findProductByNameContains(text);
    }

}
