package shop.Bucket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.Currency.CurrencyBuilder;
import shop.Currency.CurrencyCode;
import shop.Products.Product;
import shop.Products.ProductGroup;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BucketTest {
    Bucket bucket;
    Product product = new Product(1, "test", ProductGroup.NOT_FOOD, BigDecimal.TEN);

    @BeforeEach
    void setUp() {
        bucket = new Bucket();
    }

    @Test
    void addProductTest() {
        assertThrows(
                NullPointerException.class,
                () -> bucket.addProduct(null)
        );
    }

    @Test
    void addProductTest2() {
        bucket.addProduct(product);
        Product newProduct = new Product(2, "product", ProductGroup.NOT_FOOD, BigDecimal.TEN);
        bucket.addProduct(newProduct);

        assertEquals(2, (int) bucket.countProducts());
        assertTrue(bucket.getAll().keySet().contains(newProduct));
    }

    @Test
    void removeProduct() {
        bucket.addProduct(product, 3);

        bucket.removeProduct(product);

        assertEquals(2, (int) bucket.countItems());
        assertEquals(1, (int) bucket.countProducts());
    }

    @Test
    void removeProduct2() {
        bucket.addProduct(product);

        bucket.removeProduct(product);

        assertFalse(bucket.getAll().keySet().contains(product));
        assertEquals(0, (int) bucket.countProducts());
    }

    @Test
    void getAll() {
        bucket.addProduct(product);
        Product newProduct = new Product(3, "product", ProductGroup.NOT_FOOD, BigDecimal.TEN);
        bucket.addProduct(newProduct);

        assertEquals(2, (int) bucket.countProducts());
    }

    @Test
    void countProducts() {
        bucket.addProduct(product, 2);

        assertEquals(1, (int) bucket.countProducts());
    }

    @Test
    void countItems() {
        bucket.addProduct(product, 2);

        assertEquals(2, (int) bucket.countItems());
    }

    @Test
    void calculateWithUAH() {
        bucket.addProduct(product);
        bucket.addProduct(product);

        BigDecimal total = bucket.calculateTotal(CurrencyBuilder.getCurrency(CurrencyCode.UAH));

        assertEquals(3500, total.doubleValue());
    }

    @Test
    void calculateWithUSD() {
        bucket.addProduct(product);
        bucket.addProduct(product);

        BigDecimal total = bucket.calculateTotal(CurrencyBuilder.getCurrency(CurrencyCode.USD));

        assertEquals(240, total.doubleValue());
    }

    @Test
    void calculateWithEUR() {
        bucket.addProduct(product);
        bucket.addProduct(product);

        BigDecimal total = bucket.calculateTotal(CurrencyBuilder.getCurrency(CurrencyCode.EUR));

        assertEquals(300, total.doubleValue());
    }

    @Test
    void calculateWithWrongCurrency() {
        assertThrows(
                NullPointerException.class,
                () -> CurrencyBuilder.convert(4)
        );
    }
}
