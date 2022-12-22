package com.example.productmanager.controller;


import com.example.productmanager.domain.Product;
import com.example.productmanager.service.ProductService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
public class ProductForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int no;
    public String name;
    public int price;
    public int stock;

}

