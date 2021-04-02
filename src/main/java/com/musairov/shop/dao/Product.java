package com.musairov.shop.dao;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class Product implements Serializable {

    private final Integer id;
    private final String name;
    private final ProductGroup productGroup;
    private final BigDecimal price;

}
