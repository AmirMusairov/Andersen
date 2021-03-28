package shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestClass {
    Bucket bucket;
    Product product = new Product(1, "test", ProductGroup.NOT_FOOD);

    @Test
    public void productListTest() {
        ProductList productList = new ProductList();
        List<Product> products = productList.getAll();
        assertEquals(5, products.size());
    }

    @BeforeEach
    void setUp() {
        bucket = new Bucket();
    }

    @Test
    void addProduct_productNull() {
        bucket.addProduct(null);
        assertEquals(0, (int) bucket.size());
    }

    @Test
    void addProduct() {
        bucket.addProduct(product);
        Product newProduct = new Product(2, "product", ProductGroup.FOOD);
        bucket.addProduct(newProduct);

        assertEquals(2, (int) bucket.size());
        assertTrue(bucket.getAll().contains(newProduct));
    }

    @Test
    void removeProduct() {
        bucket.addProduct(product);
        bucket.addProduct(product);
        bucket.addProduct(product);

        bucket.removeProduct(product);
        bucket.removeProduct(product);

        assertTrue(bucket.getAll().contains(product));
        assertEquals(1, (int) bucket.size());
    }

    @Test
    void removeProduct2() {
        bucket.addProduct(product);

        bucket.removeProduct(product);

        assertFalse(bucket.getAll().contains(product));
        assertEquals(0, (int) bucket.size());
    }

    @Test
    void clear() {
        bucket.addProduct(product);

        bucket.clear();

        assertEquals(0, (int) bucket.size());
    }

    @Test
    void getAll() {
        bucket.addProduct(product);
        Product newProduct = new Product(1, "product", ProductGroup.FOOD);
        bucket.addProduct(newProduct);

        List<Product> result = bucket.getAll();

        assertEquals(2, result.size());
    }

    @Test
    void size() {
        bucket.addProduct(product);
        bucket.addProduct(product);

        assertEquals(2, (int) bucket.size());
    }

}
