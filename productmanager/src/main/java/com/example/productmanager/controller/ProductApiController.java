package com.example.productmanager.controller;

import com.example.productmanager.domain.Product;
import com.example.productmanager.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductApiController {
    private final ProductService productService;

    public ProductApiController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> listProducts() {
        return productService.findProducts();
    }

    @PostMapping("/products")
    public Product createProducts(@RequestBody Product product){
        Integer no = productService.join(product);
        return productService.findOne(no).get();
    }

    @PutMapping("/products/{no}")  //{no} 을 넣어준 이유가 primary key라서 그렇다.  //product는 도메인이라 계속 바뀌는데, 원래는 productForm으로 받아야한다. 바뀌지 않기 때문에.
    public Product updateProducts(@PathVariable Integer no, @RequestBody ProductForm form) {
        Product product = productService.findOne(no).get();
        product.setName(product.getName());
        product.setPrice(product.getPrice());
        product.setStock(product.getStock());

        productService.update(product);

        return productService.findOne(no).get();
    }

    @DeleteMapping("/products/{no}")
    public void deleteProduct(@PathVariable Integer no) {
        productService.delete(no);
    }


}

