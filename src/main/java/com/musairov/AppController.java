package com.musairov;

import com.musairov.shop.currency.Currency;
import com.musairov.shop.currency.CurrencyBuilder;
import com.musairov.shop.currency.CurrencyCode;
import com.musairov.shop.dao.Order;
import com.musairov.shop.dao.User;
import com.musairov.shop.dao.Warehouse;
import com.musairov.shop.repository.BucketRepository;
import com.musairov.shop.repository.ProductRepository;
import com.musairov.shop.service.AuthService;
import com.musairov.shop.service.BucketService;
import com.musairov.shop.service.OrderService;
import com.musairov.shop.utils.Menu;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static com.musairov.shop.utils.QuestionGenerator.getIntAnswer;
import static com.musairov.shop.utils.QuestionGenerator.getStringAnswer;

@Slf4j
@RequiredArgsConstructor
public class AppController {
    private final AuthService authService;
    private final Warehouse warehouse;
    private final OrderService orderService;
    private BucketService bucketService;

    public void action(Integer userInput, User user) {
        if (Objects.isNull(bucketService)) {
            initBucketController(user);
        }

        switch (userInput) {
            case 2:
                addProductToBucket();
                break;
            case 3:
                deleteProductFromBucket();
                break;
            case 4:
                showProductsInBucket();
                break;
            case 5:
                bucketService.clearBucket();
                break;
            case 6:
                makeOrder(user);
                break;
            case 7:
                showOrderHistory(user);
                break;
            case 8:
                acceptSavedOrder(user);
                break;
            case 0:
                authService.exit();
                break;
            default:
                bucketService.showProductList();
                break;
        }
    }

    private void showOrderHistory(User user) {
        List<Order> orders = orderService.getOrdersHistory(user);
        if (orders.isEmpty()) {
            System.out.println("Orders not found");
        } else {
            orders.forEach(order -> System.out.println("Order:" + order));
        }
    }

    private void showProductsInBucket() {
        if (bucketService.bucketIsEmpty()) {
            System.out.println("Bucket is empty!");
        } else {
            bucketService.showProductsInTheBucket();
        }
    }

    private void addProductToBucket() {
        bucketService.showProductList();
        Integer productId = getIntAnswer("Select a product ID:");
        int countProducts = getIntAnswer("Count of products:");
        if (bucketService.addProductToBucket(productId, countProducts)) {
            System.out.println("Product was added");
        }
    }

    private void acceptSavedOrder(User user) {
        List<Order> orders = orderService.getNotAcceptedOrders(user);
        if (orders.isEmpty()) {
            System.out.println("Orders not found");
        } else {
            orders.forEach(order -> System.out.println("Order:" + order));
            Integer orderId = getIntAnswer("Order id:");
            Integer accept = getIntAnswer("Accept order? (1 - Yes, 0 - No):");
            if (accept == 1) {
                orderService.acceptOrder(user, orderId);
                System.out.println("Order accepted!");
            } else {
                System.out.println("Order canceled!");
            }
        }
    }

    private void makeOrder(User user) {
        Menu.showCurrencyMenu();
        Integer currencyNumber = getIntAnswer("Select a currency:");
        CurrencyCode currencyCode = CurrencyBuilder.convert(currencyNumber);
        if (Objects.isNull(currencyCode)) {
            System.out.println("Order canceled!");
        } else {
            Currency currency = CurrencyBuilder.getCurrency(currencyCode);
            BigDecimal total = bucketService.getTotal(currency);
            System.out.println("Total:" + total);
            int confirm = getIntAnswer(" Confirm? (1 - Yes, 0 - No)");
            if (confirm == 1) {
                orderService.makeOrder(user, total, true);
                bucketService.clearBucket();
                System.out.printf("Your order accepted, you must pay %s %s", CurrencyCode.UAH, total);
            } else if (confirm == 0) {
                Integer saveOrder = getIntAnswer("Save order? (1 - Yes, 0 - No)");
                if (saveOrder.equals(1)) {
                    orderService.makeOrder(user, total, false);
                    System.out.println("Order saved!");
                } else {
                    System.out.println("Order canceled!");
                }
                bucketService.clearBucket();
            } else {
                bucketService.clearBucket();
                System.out.println("Order canceled!");
            }
        }
    }

    private void deleteProductFromBucket() {
        if (bucketService.bucketIsEmpty()) {
            System.out.println("Bucket is empty!");
        } else {
            bucketService.showProductsInTheBucket();
            Integer productId = getIntAnswer("Select a product ID:");
            Integer countProducts = getIntAnswer("Count of products:");
            if(bucketService.deleteProductFromTheBucket(productId, countProducts)) {
                System.out.println("Product was removed!");
            }
        }
    }

    public User authAction(Integer userInput) {
        User user = null;
        switch (userInput) {
            case 1:
                String login = getStringAnswer("Login:");
                String password = getStringAnswer("Password:");
                user = authService.login(login, password);
                if (Objects.nonNull(user)) {
                    System.out.println("Welcome " + login + "!");
                } else {
                    System.out.println("Invalid login or password");
                }
                return user;
            case 2:
                login = getStringAnswer("Login:");
                password = getStringAnswer("Password:");
                user = authService.registration(login, password);
                if (Objects.nonNull(user)) {
                    System.out.println("Welcome " + login + "!");
                } else {
                    System.out.println("Invalid login or password");
                }
                return user;
            case 0:
                authService.exit();
                System.exit(0);
            default:
                return user;
        }
    }

    public User authorization() {
        Integer userInput;
        User user;

        do {
            Menu.showAuthMenu();
            userInput = getIntAnswer("Insert number from 0 to 2");
            user = authAction(userInput);
        } while (Objects.isNull(user));

        return user;
    }

    private void initBucketController(User user) {
        bucketService = new BucketService(
                warehouse,
                new BucketRepository(user, new ProductRepository(), warehouse)
        );
    }
}
