package shop;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class Bucket {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        if (Objects.nonNull(product)) {
            products.add(product);
        }
    }

    public void removeProduct(Product product) {
        if (Objects.nonNull(product)) {
            products.remove(product);
        }
    }

    public void clear() {
        products.clear();
    }

    public List<Product> getAll() {
        return products;
    }

    public Integer size() {
        return products.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}
