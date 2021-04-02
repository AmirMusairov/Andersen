package shop.Products;

import java.math.BigDecimal;
import java.util.List;

public class ProductList {
    public List<Product> products;

    ProductList() {
        products = List.of(
                new Product(0, "Mango", ProductGroup.FOOD, BigDecimal.valueOf(5.0)),
                new Product(1, "Apple", ProductGroup.FOOD, BigDecimal.valueOf(2.5)),
                new Product(2, "Knife", ProductGroup.NOT_FOOD, BigDecimal.valueOf(35.6)),
                new Product(3, "Forks and Spoons", ProductGroup.NOT_FOOD, BigDecimal.valueOf(54.5)),
                new ShelfLifeProduct(4, "Cheese", ProductGroup.FERMENTED_MILK, BigDecimal.valueOf(105.8)),
                new ShelfLifeProduct(5, "Butter", ProductGroup.FERMENTED_MILK, BigDecimal.valueOf(45.9))

        );
    }

    public List<Product> getAll() {
        return products;
    }

    public Product getById(Integer id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
