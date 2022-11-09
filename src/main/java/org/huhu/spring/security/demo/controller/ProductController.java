package org.huhu.spring.security.demo.controller;

import org.huhu.spring.security.demo.entity.Product;
import org.huhu.spring.security.demo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/find")
    public List<Product> findProducts() {
        return productService.sellProducts();
    }

}
