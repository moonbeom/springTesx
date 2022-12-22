package com.example.productmanager.repository;

import com.example.productmanager.domain.Product;

import java.util.*;

public class MemoryProductRepository implements ProductRepository {

    private static Map<Integer, Product> store = new HashMap<>(); //저장할거는 멤버가된다.
    private static int sequence = 0;
    @Override
    public Product save(Product product) {
        product.setNo(++sequence);
        store.put(product.getNo(), product);
        return product;
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
