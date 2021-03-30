package shop;

import lombok.extern.slf4j.Slf4j;
import shop.Bucket.Bucket;
import shop.Bucket.Saver;
import shop.Currency.Currency;
import shop.Currency.CurrencyBuilder;
import shop.Currency.CurrencyCode;
import shop.Products.Warehouse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Objects;

@Slf4j
public class Launcher {
    private static final String BUCKET_FILE_NAME = "bucket.txt";
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final Controller controller;
    private static final Bucket bucket;
    private static final Warehouse warehouse;

    static {
        bucket = Saver.load(BUCKET_FILE_NAME);
        warehouse = new Warehouse();
        controller = new Controller(warehouse, bucket);
    }

    public static void main(String[] args) {
        warehouse.boot();
        Integer userInput;

        do {
            Menu.showMenu();
            userInput = askQuestion("Insert number from 0 to 5");
            action(userInput);
        } while (userInput != 0);
    }

    private static void action(Integer userInput) {
        switch (userInput) {
            case 2:
                controller.showProductList();
                Integer productId = askQuestion("Select a product:");
                int countProducts = askQuestion("Count of products:");
                boolean wasAdded = controller.addProductToBucket(productId, countProducts);
                if (wasAdded) {
                    System.out.println("Product added to bucket");
                }
                break;
            case 3:
                controller.showProductsInTheBucket();
                productId = askQuestion("Select a product:");
                countProducts = askQuestion("Count of products:");
                boolean wasRemoved = controller.deleteProductFromTheBucket(productId, countProducts);
                if (wasRemoved) {
                    System.out.println("Product removed from bucket");
                }
                break;
            case 4:
                boolean notEmpty = controller.showProductsInTheBucket();
                if (!notEmpty) {
                    System.out.println("Bucket is empty!");
                }
                break;
            case 5:
                controller.clearBucket();
                System.out.println("Your bucket is cleaned");
                break;
            case 6:
                Menu.showCurrencyMenu();
                Integer currencyNumber = askQuestion("Select a currency:");
                CurrencyCode currencyCode = CurrencyBuilder.convert(currencyNumber);
                if (Objects.isNull(currencyCode)) {
                    System.out.println("Your order canceled");
                } else {
                    Currency currency = CurrencyBuilder.getCurrency(currencyCode);
                    BigDecimal total = controller.makeOrder(currency);
                    System.out.printf("Your order accepted, you must pay %s %s", CurrencyCode.UAH, total);
                }
                break;
            case 0:
                Saver.saveBucket(bucket, BUCKET_FILE_NAME);
                controller.exit();
                break;
            default:
                controller.showProductList();
        }

    }

    private static Integer askQuestion(String question) {
        System.out.println(question);
        try {
            return Integer.parseInt(reader.readLine());
        } catch (Exception e) {
            return -1;
        }
    }
}
