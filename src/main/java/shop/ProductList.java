package shop;

import java.util.List;

public class ProductList {
    public List<Product> products;

    ProductList() {
        products = List.of(
                new Product(0, "Mango", ProductGroup.FOOD),
                new Product(1, "Apple", ProductGroup.FOOD),
                new Product(2, "Knife", ProductGroup.NOT_FOOD),
                new Product(3, "Meat", ProductGroup.FOOD),
                new Product(4, "Forks and Spoons", ProductGroup.NOT_FOOD)
        );
    }

    public List<Product> getAll() {
        return products;
    }

    public Product getById(int id) {
        return products.get(id);
    }
}
