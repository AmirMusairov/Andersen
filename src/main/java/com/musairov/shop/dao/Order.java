package com.musairov.shop.dao;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@ToString
@AllArgsConstructor
public class Order {
    private Integer id;
    private LocalDate createdAt;
    private UUID userId;
    private BigDecimal total;
    private boolean accepted;
}
