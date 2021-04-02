package com.musairov.shop.utils;

public class DbConnection {
    protected static final String DB_USER = "user";
    protected static final String DB_PASSWORD = "password";
    protected static final String DB_URL = "jdbc:mysql://localhost:3306/internet-shop?serverTimezone=UTC";

    public DbConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Mysql Driver Exception: " + e.getMessage());
        }
    }
}
