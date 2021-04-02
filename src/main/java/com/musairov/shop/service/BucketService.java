package com.musairov.shop.service;

import com.musairov.shop.currency.Currency;
import com.musairov.shop.dao.Warehouse;
import com.musairov.shop.repository.BucketRepository;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

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
        try {
            bucketRepository.addProduct(warehouse.getById(productId, count), count);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("No products in warehouse");
            return false;
        } catch (NullPointerException e) {
            System.out.println("Product not found!");
            return false;
        }
    }

    public boolean deleteProductFromTheBucket(Integer productId, Integer count) {
        if (bucketRepository.isEmpty()) {
            System.out.println("Your bucket is empty!");
            return false;
        }

        try {
            bucketRepository.removeProduct(warehouse.getById(productId), count);
            return true;
        } catch (NullPointerException e) {
            System.out.println("Product not found!");
            return false;
        } catch (IllegalArgumentException e) {
            System.out.printf("Wrong number!", count);
            return false;
        }
    }

    public void showProductsInTheBucket() {
        bucketRepository.getAll()
                .forEach((product, count) -> System.out.println(product + ": count = " + count));
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
