package org.huhu.spring.security.demo.controller;

import org.huhu.spring.security.demo.entity.Product;
import org.huhu.spring.security.demo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/sell")
    public List<Product> sell() {
        // 模拟客户端的传参
        List<Product> products = new LinkedList<>();
        products.add(new Product("beer", "jack"));
        products.add(new Product("candy", "jack"));
        products.add(new Product("chocolate", "rose"));

        // 注意@PreFilter会直接修改参数的值
        logger.info("before @PreFilter, list size is {}", products.size());
        List<Product> result = productService.sellProducts(products);
        logger.info("after @PreFilter, list size is {}", products.size());

        return result;
    }

}
