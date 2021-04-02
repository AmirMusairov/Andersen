package com.musairov.shop.dao;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Product implements Serializable {

    private Integer id;
    private String name;
    private ProductGroup productGroup;
    private BigDecimal price;

    public Product(String name, BigDecimal price, ProductGroup productGroup) {
        this.name = name;
        this.price = price;
        this.productGroup = productGroup;
    }

    public Product(Integer id, String name, BigDecimal price, ProductGroup productGroup) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productGroup = productGroup;
    }
}
