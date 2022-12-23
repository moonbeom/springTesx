package com.example.productmanager.repository;

import com.example.productmanager.domain.Product;

import java.util.*;
 // 1-3프로덕트리포지토리를 상속 하는 메모리프로덕트리포지토리를 만들어보자.
 // 임플먼트를 사용하는 이유는 인터페이스 상속이기 때문에 사용한 것이다.
public class MemoryProductRepository implements ProductRepository {

    // 해쉬맵을 사용한 이유는 Map 인터페이스를 구현한 대표적인 Map 컬렉션이다.
     // Map은 키와 값으로 구성된 Entry객체를 저장하는 구조를 가지고 있는 자료구조
     // Entry 객체란
    // Integer는 숫자를 주기 위해, 프로덕트는 프로덕트 타입이기 떄문에 사용한 것이다.
     //저장공간 store를 만든다.
     // 1-4 오버라이드할
    private static Map<Integer, Product> store = new HashMap<>(); //저장할거는 프로덕트가된다.
    private static int sequence = 0;
    @Override
    public Product save(Product product) {
        product.setNo(++sequence); // set은 중복값을 사용할 수 없다.
        store.put(product.getNo(), product); // 스토어에 프로덕트 no를 넣어보자.
        return product; //그리고 프로덕트를 반환한다.
    }

    @Override
    public Optional<Product> findByNo(int no) {
        return Optional.ofNullable(store.get(no));
    }


    @Override
    public Optional<Product> findByName(String name) {
        return store.values().stream()
                .filter(product -> product.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<Product>(store.values());
    }

    @Override
    public void delete(Product product) {

    }
    public void clearStore() {
        store.clear();
    }

}
