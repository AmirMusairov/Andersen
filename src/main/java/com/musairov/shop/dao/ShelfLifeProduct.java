package com.musairov.shop.dao;

import com.musairov.shop.utils.ExpiredDateProducts;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ShelfLifeProduct extends Product {
    @ExpiredDateProducts(shelfLife = 4)
    private LocalDate expiredDate;

    ShelfLifeProduct(Integer id, String name, BigDecimal price, ProductGroup productGroup) {
        super(id, name, price, productGroup);

    }
}
