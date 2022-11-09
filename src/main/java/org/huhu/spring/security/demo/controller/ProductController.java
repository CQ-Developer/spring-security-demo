package org.huhu.spring.security.demo.controller;

import org.huhu.spring.security.demo.entity.Product;
import org.huhu.spring.security.demo.service.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{text}")
    public List<Product> hello(@PathVariable String text, Authentication authentication) {
        return productService.findAll(text);
    }

}
