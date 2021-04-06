package com.musairov;

import com.musairov.shop.dao.User;
import com.musairov.shop.repository.OrderRepository;
import com.musairov.shop.repository.ProductRepository;
import com.musairov.shop.repository.UserRepository;
import com.musairov.shop.repository.WarehouseRepository;
import com.musairov.shop.service.AuthService;
import com.musairov.shop.service.OrderService;
import com.musairov.shop.utils.Menu;
import org.apache.taglibs.standard.tag.common.sql.DataSourceWrapper;
import org.springframework.jdbc.core.JdbcTemplate;

import static com.musairov.shop.utils.QuestionGenerator.getIntAnswer;

public class Application {
    private final AppController appController;

    public Application() {
        appController = new AppController(
                new AuthService(new UserRepository(new JdbcTemplate(), new DataSourceWrapper())),
                new WarehouseRepository(new ProductRepository(new JdbcTemplate(), new DataSourceWrapper()), new JdbcTemplate(), new DataSourceWrapper()),
                new OrderService(new OrderRepository(new JdbcTemplate(), new DataSourceWrapper()))
        );
    }

    public static void main(String[] args) {
        new Application().start();
    }

    public void start() {
        User user = appController.authorization();

        int userInput;
        do {
            Menu.showMenu();
            userInput = getIntAnswer("Insert number from 0 to 8");
            appController.action(userInput, user);
        } while (userInput != 0);
    }
}
