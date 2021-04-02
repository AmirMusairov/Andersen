package com.musairov.shop.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Menu {
    public static void showMenu() {
        System.out.println("=========== MENU ==========");
        System.out.println("1. Show product list");
        System.out.println("2. Add product to the bucket");
        System.out.println("3. Delete product from the bucket");
        System.out.println("4. Show products in the bucket");
        System.out.println("5. Clear bucket");
        System.out.println("6. Make an order");
        System.out.println("7. Orders history");
        System.out.println("8. Accept saved orders");
        System.out.println("0. Exit");
        System.out.println("==========================");
    }

    public static void showCurrencyMenu() {
        System.out.println("=========== CURRENCY LIST ==========");
        System.out.println("1. Ukrainian hryvnia");
        System.out.println("2. American dollar");
        System.out.println("3. Euro");
        System.out.println("0. Cancel");
        System.out.println("====================================");
    }

    public static void showAuthMenu() {
        System.out.println("========== Sign up / Sign in ==========");
        System.out.println("1. Sign in");
        System.out.println("2. Sign up");
        System.out.println("0. Exit");
        System.out.println("=======================================");
    }
}
