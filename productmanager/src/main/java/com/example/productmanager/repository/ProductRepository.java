package com.example.productmanager.repository;

import com.example.productmanager.domain.Product;

import java.util.List;
import java.util.Optional;
// 1-2 프로덕트 인터페이스 리포지토리를 만들었다.
// 1-3 이제 리포지토리의 메모리 구현체를 만들어보자.
public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findByNo(int no);
    Optional<Product> findByName(String name);
    List<Product> findAll();

    void delete(Product product);
}
