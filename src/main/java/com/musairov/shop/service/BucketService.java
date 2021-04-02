package com.musairov.shop.service;

import com.musairov.shop.currency.Currency;
import com.musairov.shop.dao.Product;
import com.musairov.shop.dao.Warehouse;
import com.musairov.shop.repository.BucketRepository;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Map;

@Slf4j
public class BucketService {
    private final Warehouse warehouse;
    private final BucketRepository bucketRepository;

    public BucketService(Warehouse warehouse, BucketRepository bucketRepository) {
        this.warehouse = warehouse;
        this.bucketRepository = bucketRepository;
    }

    public void showProductList() {
        warehouse.getAll()
                .forEach((product, count) -> System.out.println(product + ": count = " + count));
    }

    public boolean addProductToBucket(Integer productId, Integer count) {
        boolean added = false;
        try {
            added = bucketRepository.addProduct(warehouse.getById(productId, count), count);
        } catch (IllegalArgumentException e) {
            System.out.println("No products in warehouse");
        } catch (NullPointerException e) {
            System.out.println("Product not found!");
        }
        return added;
    }

    public boolean addProductToBucket(Integer productId) {
        return addProductToBucket(productId, 1);
    }

    public boolean deleteProductFromTheBucket(Integer productId, Integer count) {
        if (bucketRepository.isEmpty()) {
            System.out.println("Your bucket is empty!");
            return false;
        }

        boolean deleted = false;
        try {
            bucketRepository.removeProduct(warehouse.getById(productId), count);
            deleted = true;
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.printf("Something went wrong", count);
        }
        return deleted;
    }

    public boolean deleteProductFromTheBucket(Integer productId) {
        return deleteProductFromTheBucket(productId, 1);
    }

    public void showProductsInTheBucket() {
        bucketRepository.getAll()
                .forEach((product, count) -> System.out.println(product + ": count = " + count));
    }

    public Map<Product, Integer> getProducts() {
        return bucketRepository.getAll();
    }

    public void clearBucket() {
        bucketRepository.clear();
        System.out.println("Bucket is cleaned");
    }

    public boolean bucketIsEmpty() {
        return bucketRepository.isEmpty();
    }

    public BigDecimal getTotal(Currency currency) {
        return bucketRepository.calculateTotal(currency);
    }
}
