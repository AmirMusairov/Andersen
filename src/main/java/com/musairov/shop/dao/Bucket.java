package com.musairov.shop.dao;

import lombok.Getter;
import lombok.ToString;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@ToString
public class Bucket {
    private Integer id;
    private User user;
    private Map<Product, Integer> products = new LinkedHashMap<>();

    public Bucket(Integer id, User user) {
        this.id = id;
        this.user = user;
    }
}
