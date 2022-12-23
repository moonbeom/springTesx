package com.example.productmanager.domain;
//얘가 멤버 대체를 한다. 얘가 리포지토리다.

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
//@Builder
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int no;
    public String name;
    public int price;
    public int stock;
}

// 1-1 도메인 만들고 인터페이스 리포지토리를 만들어보자.