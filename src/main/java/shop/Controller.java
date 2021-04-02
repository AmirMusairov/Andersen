package shop;

import lombok.AllArgsConstructor;
import shop.Bucket.Bucket;
import shop.Currency.Currency;
import shop.Products.Warehouse;

import java.math.BigDecimal;

@AllArgsConstructor
public class Controller {
    private final Warehouse warehouse;
    private final Bucket bucket;

    public void showProductList() {
        warehouse.getProducts()
                .forEach((product, count) -> System.out.println(product + ": count = " + count));
    }

    public boolean addProductToBucket(Integer productId, Integer count) {
        try {
            bucket.addProduct(warehouse.getById(productId, count), count);
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
        if (bucket.isEmpty()) {
            System.out.println("Your bucket is empty!");
            return false;
        }

        try {
            bucket.removeProduct(warehouse.getById(productId), count);
            return true;
        } catch (NullPointerException e) {
            System.out.println("Product not found!");
            return false;
        } catch (IllegalArgumentException e) {
            System.out.printf("Wrong number!", count);
            return false;
        }
    }

    public boolean showProductsInTheBucket() {
        if (!bucket.isEmpty()) {
            bucket.getAll()
                    .forEach((product, count) -> System.out.println(product + ": count = " + count));
            return true;
        }

        return false;
    }

    public void clearBucket() {
        bucket.clear();
    }

    public void exit() {
        System.out.println("Goodbye, see you next time!");
    }

    public BigDecimal makeOrder(Currency currency) {
        return bucket.calculateTotal(currency);
    }
}
