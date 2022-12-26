package com.example.productmanager.service;

import com.example.productmanager.domain.Product;
import com.example.productmanager.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service // 1-5 서비스를 만들어보자.
public class ProductService {
    private final ProductRepository productRepository; //ProductRepository 필드 선언.

    public ProductService(ProductRepository productRepository) { // ProductService 생성자 선언.
        this.productRepository = productRepository;
    }

    public int join(Product product) { // 상품 만들기.
        validateDuplicateProduct(product);  //중복 상품 체크. 중복상품체크 메소드를 만든다.
        productRepository.save(product); //인터페이스 ProductRepository save 안에 product 상품 저장.
        return product.getNo(); // product no를 리턴한다.

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
// 1-6 ProductService, ProductRepository를 사용할 수 있게 ProductController 의존 관계를 설정한다.

    @Transactional //Trnasactional을 사용한 이유는 Yo 애노테이션이 있으면, 이전 트랜잭션을 시작하고,
    //  완료 후에 항상 롤백한다. 이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지
    // 않는다.
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
//    Optional<Product>
    public Optional<Product> findOne(Integer productNo) {

        return productRepository.findByNo(productNo);
    }
}
