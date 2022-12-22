package com.example.productmanager.service;

import com.example.productmanager.domain.Product;
import com.example.productmanager.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public int join(Product product) {
        validateDuplicateProduct(product);  //중복 상품 검증.
        productRepository.save(product);
        return product.getNo();

    }

    private void validateDuplicateProduct(Product product) {
        productRepository.findByName(product.getName())
                .ifPresent(p -> {
                    throw new IllegalStateException("이미 존재하는 상품입니다.");
                });
    }

    public List<Product> findProducts() {
        return productRepository.findAll();
    }
    @Transactional
    public void update(Product product) {
        Product productToUpdate = productRepository.findByNo(product.getNo()).get(); // 식별만 함
        // 레포지토리를 DB에 연결하기 위해서 프로덕트 안에 있는 no를 기준으로 findByNo하고 데이터를 get을 이용하여 가져옴.
        // 조회하고 가져와서 대입을 한거다.
        productToUpdate.setName(product.getName());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setStock(product.getStock());
    }
    public void delete(Integer no) {
        productRepository.delete(productRepository.findByNo(no).get());

    }

}

