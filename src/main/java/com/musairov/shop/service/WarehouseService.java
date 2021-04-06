package com.musairov.shop.service;

import com.musairov.shop.dao.Product;
import com.musairov.shop.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class WarehouseService  {
    private final WarehouseRepository warehouseRepository;


    public void addProduct(Product product, Integer count) {
        warehouseRepository.addProduct(product, count);
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public Product getById(Integer productId, Integer count) {
        return warehouseRepository.getById(productId, count);
    }

    public Product getById(Integer id) {
        return getById(id, 1);
    }

    public Integer countItems() {
        return warehouseRepository.countItems();
    }

    public Integer countProducts() {
        return warehouseRepository.countProducts();
    }

    public Integer countProductById(Integer productId) {
        return warehouseRepository.countProductById(productId);
    }

    public boolean isEmpty() {
        return warehouseRepository.isEmpty();
    }

    public Map<Product, Integer> getAll() {
        return warehouseRepository.getAll();
    }

    public boolean updateCount(Integer productId, Integer count) {
        return warehouseRepository.update(productId, count);
    }

    public boolean increaseCountProducts(Integer productId, Integer count) {
        return warehouseRepository.increaseCountProducts(productId, count);
    }

    public boolean reduceCountProducts(Integer productId, Integer count) {
        return warehouseRepository.reduceCountProducts(productId, count);
    }
}