package com.example.productmanager.controller;

import com.example.productmanager.domain.Product;
import com.example.productmanager.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductApiController {
    private final ProductService productService;

    public ProductApiController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> listProducts() {
        List<Product> products = productService.findProducts();
        return products;
    }
}

