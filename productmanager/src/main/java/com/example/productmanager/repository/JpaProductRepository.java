package com.example.productmanager.repository;

import com.example.productmanager.domain.Product;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaProductRepository implements ProductRepository {

    private final EntityManager em;

    public JpaProductRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Product save(Product product) {
        em.persist(product);
        return product;
    }

    @Override
    public Optional<Product> findByNo(int no) {
        Product product = em.find(Product.class, no);
        return Optional.ofNullable(product);
    }

    @Override
    public Optional<Product> findByName(String name) {
        List<Product> result = em.createQuery("select p from product p where p.name = :name",
                        Product.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Product> findAll() {
        return em.createQuery("select p from Product p", Product.class).
                getResultList();
    }

    @Override
    public void delete(Product product) {
        em.persist(product);


    }
//
}

