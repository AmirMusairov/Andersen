package shop.Products;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Warehouse {
    private Map<Product, Integer> products = new LinkedHashMap<>();

    public void addProduct(Product product, Integer count) {
        count = checkCount(count);
        checkExpiredDate(product);

        Integer currentCount = products.get(product);
        if (Objects.isNull(currentCount)) {
            products.put(product, count);
        } else {
            products.put(product, count + currentCount);
        }
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public Product getById(Integer id, Integer count) {
        count = checkCount(count);
        Product product = products.keySet().stream()
                .filter(prod -> prod.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (Objects.nonNull(product)) {
            Integer currentCount = products.get(product);

            if (currentCount >= count) {
                products.put(product, currentCount - count);
                return product;
            } else {
                throw new IllegalArgumentException("To many products!");
            }
        }

        throw new NullPointerException("Product not found!");
    }

    public Product getById(Integer id) {
        return getById(id, 1);
    }

    public Integer countItems() {
        return products.values().stream().reduce(0, Integer::sum);
    }

    public Integer countProducts() {
        return products.size();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    private void checkExpiredDate(Product product) {
        if (product instanceof ShelfLifeProduct) {
            try {
                Field expiredDateField = product.getClass().getDeclaredField("expiredDate");
                expiredDateField.setAccessible(true);
                ExpiredDateProducts annotation = expiredDateField.getDeclaredAnnotation(ExpiredDateProducts.class);
                int lifeDays = annotation.shelfLife();
                expiredDateField.set(product, LocalDate.now().plusDays(lifeDays));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException("Wrong ExpiredDate");
            }
        }
    }

    private Integer checkCount(Integer count) {
        return count < 0 ? 0 : count;
    }

    public void boot() {
        addProduct( new Product(0,"Mango", ProductGroup.FOOD,BigDecimal.valueOf(5.0)));
        addProduct(new Product(1, "Apple",ProductGroup.FOOD,BigDecimal.valueOf(2.5)));
        addProduct(new Product(2, "Knife",ProductGroup.NOT_FOOD,BigDecimal.valueOf(35.6)));
        addProduct(new Product(3, "Forks and Spoons",ProductGroup.NOT_FOOD,BigDecimal.valueOf(54.5)));
        addProduct(new ShelfLifeProduct(4,"Cheese",ProductGroup.FERMENTED_MILK,BigDecimal.valueOf(105.8)));
        addProduct(new ShelfLifeProduct(5,"Butter",ProductGroup.FERMENTED_MILK,BigDecimal.valueOf(45.9)));
        addProduct(new Product(0,"Meat", ProductGroup.FOOD,BigDecimal.valueOf(100.3)),100);
        addProduct(new ShelfLifeProduct(5,"Mayonnaise",ProductGroup.FERMENTED_MILK,BigDecimal.valueOf(11.3)), 123);
    }
}