package com.example.productmanager.repository;

import com.example.productmanager.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataRepository extends JpaRepository<Product,
        Integer>, ProductRepository {
}
