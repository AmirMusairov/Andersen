package Patterns.Singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Class that get connection with db is an implementation of Singleton pattern
public class SingletonImpl {

    public static final String URL = "url";
    private static final String PARAMS = "params";
    public static final String USER = "user";
    public static final String PASSWORD = "password";

    private static SingletonImpl instance;

    private SingletonImpl() {
    }

    public synchronized static SingletonImpl getInstance() {
        if (instance == null) {
            instance = new SingletonImpl();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            Class.forName("driver");
            return DriverManager.getConnection(URL + PARAMS, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}