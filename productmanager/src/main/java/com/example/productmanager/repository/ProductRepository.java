package com.example.productmanager.repository;

import com.example.productmanager.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findByNo(int no);
    Optional<Product> findByName(String name);
    List<Product> findAll();

    void delete(Product product);
}
