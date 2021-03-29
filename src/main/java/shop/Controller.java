package shop;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Controller {
    private final ProductList productList;
    private final Bucket bucket;

    public void showProductList() {
        productList.getAll().forEach(System.out::println);
    }

    public void addProductToBucket(int productId) {
        bucket.addProduct(productList.getById(productId));
    }

    public boolean deleteProductFromTheBucket(int productId) {
        if (!bucket.isEmpty()) {
            bucket.removeProduct(productList.getById(productId));
            return true;
        }

        return false;
    }

    public boolean showProductsInTheBucket() {
        if (!bucket.isEmpty()) {
            bucket.getAll().forEach(System.out::println);
            return true;
        }

        return false;
    }

    public void clearBucket() {
        bucket.clear();
    }

    public void exit() {
        System.out.println("See you next time!");
    }
}
