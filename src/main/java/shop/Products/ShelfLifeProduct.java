package shop.Products;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ShelfLifeProduct extends Product {
    @ExpiredDateProducts(shelfLife = 4)
    private LocalDate expiredDate;

    ShelfLifeProduct(Integer id, String name, ProductGroup productGroup, BigDecimal price) {
        super(id, name, productGroup, price);

    }
}
