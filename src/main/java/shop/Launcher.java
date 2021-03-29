package shop;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Slf4j
public class Launcher {
    private static int choice;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Controller controller = new Controller(new ProductList(), new Bucket());

    public static void main(String[] args) {
        do {
            showChoices();
            choice = askQuestion("Choose from the suggested");
            action(choice);
        } while (choice != 0);
    }

    private static void action(int choice) {
        switch (choice) {
            case 2:
                controller.showProductList();
                controller.addProductToBucket(
                        askQuestion("Select a product: "));
                break;
            case 3:
                boolean notEmpty = controller.showProductsInTheBucket();
                if (notEmpty) {
                    controller.deleteProductFromTheBucket(
                            askQuestion("Select a product: "));
                    System.out.println(" !!!Product was removed!!! ");
                } else {
                    System.out.println(" Bucket is empty :( ");
                }
                break;
            case 4:
                notEmpty = controller.showProductsInTheBucket();
                if (!notEmpty) {
                    System.out.println(" Bucket is empty :( ");
                }
                break;
            case 5:
                controller.clearBucket();
                System.out.println(" Your Bucket is cleared :) ");
                break;
            case 0:
                controller.exit();
                break;
            default:
                controller.showProductList();
        }

    }

    private static int askQuestion(String question) {
        System.out.println(question);
        try {
            return Integer.parseInt(reader.readLine());
        } catch (Exception e) {
            return 1;
        }
    }

    private static void showChoices() {
        System.out.println("==========WElCOME==========");
        System.out.println("1. Show product list");
        System.out.println("2. Add product to the bucket");
        System.out.println("3. Remove product from the bucket");
        System.out.println("4. Show products in the bucket");
        System.out.println("5. Clear bucket");
        System.out.println("0. Exit");
        System.out.println("===========================");
    }
}
