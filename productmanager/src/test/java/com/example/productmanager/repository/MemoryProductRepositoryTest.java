package com.example.productmanager.repository;

import com.example.productmanager.domain.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Member;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemoryProductRepositoryTest {

    MemoryProductRepository repository = new MemoryProductRepository();
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    void save() {
        Product product = new Product();
        product.setName("spring");

        repository.save(product);

        Product result = repository.findByNo(product.getNo()).get();
        assertThat(result).isEqualTo(product);

    }

    @Test
    void findByNo() {}


    @Test
    void 상품등록() {
        Product product1 = new Product();
        product1.setName("spring1");
        repository.save(product1);

        Product result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(product1);

    }


    @Test
    void 상품조회() {
        Product product1 = new Product();
        product1.setName("spring1");
        repository.save(product1);


        List<Product> result = repository.findAll();

        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    void clearStore() {
    }
}