package com.example.productmanager.service;

import com.example.productmanager.domain.Product;
import com.example.productmanager.repository.MemoryProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    ProductService productService;
    MemoryProductRepository productRepository;



    @BeforeEach
    public void beforeEach() {
        productRepository = new MemoryProductRepository();
        productService = new ProductService(productRepository);
    }

    @AfterEach
    public void afterEach() {
        productRepository.clearStore();
    }

    @Test
    void 상품등록() throws Exception {

        Product product = new Product();
        product.setName("hello");

        Integer saveNo = productService.join(product);

        Product findProduct = productRepository.findByNo(saveNo).get();
        assertEquals(product.getName(), findProduct.getName());
    }

    @Test
    void 상품조회() {

        Product product1 = new Product();
        product1.setName("spring");

        Product product2 = new Product();
        product2.setName("spring");

        productService.join(product1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> productService.join(product2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 상품입니다.");
    }

}