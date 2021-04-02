package shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.Products.Product;
import shop.Products.ProductGroup;
import shop.Products.Warehouse;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {
    private Warehouse warehouse = new Warehouse();
    private Product product = new Product(1, "test product", ProductGroup.NOT_FOOD, BigDecimal.TEN);

    @BeforeEach
    void setUp() {
    }

    @Test
    void addProduct() {
        warehouse.addProduct(product, 3);

        assertEquals(1, (int) warehouse.countProducts());
        assertEquals(3, (int) warehouse.countItems());
    }

    @Test
    void countProducts() {
        assertEquals(0, (int) warehouse.countProducts());
    }

    @Test
    void countProducts2() {
        warehouse.addProduct(product);
        warehouse.addProduct(product);
        warehouse.addProduct(product);

        assertEquals(1, (int) warehouse.countProducts());
    }

    @Test
    void countItems() {
        assertEquals(0, (int) warehouse.countItems());
    }

    @Test
    void countItems2() {
        warehouse.addProduct(product);
        warehouse.addProduct(product);
        warehouse.addProduct(product);
        warehouse.addProduct(product, 3);

        assertEquals(6, (int) warehouse.countItems());
    }

    @Test
    void isEmptyTest() {
        warehouse.addProduct(product);

        assertFalse(warehouse.isEmpty());
    }

    @Test
    void isEmptyTest2() {
        assertTrue(warehouse.isEmpty());
    }

    @Test
    void getByIdTest() {
        warehouse.addProduct(product, 10);

        Product prod = warehouse.getById(product.getId(), 8);

        assertEquals(2, (int) warehouse.countItems());
        assertTrue(prod.equals(product));
    }

    @Test
    void getByIdTest2() {
        warehouse.addProduct(product, 10);

        assertThrows(
                RuntimeException.class,
                () -> warehouse.getById(product.getId(), 18)
        );
    }

    @Test
    void getByIdTest3() {
        warehouse.addProduct(product, 10);

        Product prod = warehouse.getById(product.getId());

        assertTrue(product.equals(prod));
        assertEquals(9, (int) warehouse.countItems());
    }

    @Test
    void getByIdTest4() {
        assertThrows(
                RuntimeException.class,
                () -> warehouse.getById(product.getId())
        );
    }

    @Test
    void getByIdTest5() {
        assertThrows(
                RuntimeException.class,
                () -> warehouse.getById(-123)
        );
    }
}
