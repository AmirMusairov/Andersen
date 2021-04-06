package com.musairov.shop.repository;

import com.musairov.shop.dao.Product;
import com.musairov.shop.dao.ProductGroup;
import com.musairov.shop.dao.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    public boolean create(String name, BigDecimal price, ProductGroup productGroup) {
        int rows = 0;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            rows = jdbcTemplate.update("insert into products (name, price, group) values (?, ?, ?)", name, price, productGroup.name());

            connection.commit();
        } catch (Exception e) {
            System.out.println("Product Exception");
            try {
                if (Objects.nonNull(connection)) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Rollback Exception");
            }
        }

        return rows != 0;
    }

    public Product getById(Integer id) {
        Product product = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            product = jdbcTemplate.queryForObject(
                    "select * from products where id = ?",
                    new Object[]{id},
                    new ProductMapper());
            connection.commit();

        } catch (Exception e) {
            System.out.println("Product Exception");
            try {
                if (Objects.nonNull(connection)) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Rollback Exception");
            }
        }

        return product;
    }

    public List<Product> getAll() {
        List<Product> products = Collections.emptyList();
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            products = jdbcTemplate.query("select * from products", new ProductMapper());
            connection.commit();

        } catch (Exception e) {
            System.out.println("Product Exception");
            try {
                if (Objects.nonNull(connection)) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Rollback Exception");
            }
        }

        return products;
    }
}
