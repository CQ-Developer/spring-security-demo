package org.huhu.spring.security.demo.service;

import lombok.RequiredArgsConstructor;
import org.huhu.spring.security.demo.entity.Product;
import org.huhu.spring.security.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

}
