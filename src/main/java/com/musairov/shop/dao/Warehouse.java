package com.musairov.shop.dao;

import com.musairov.shop.repository.ProductRepository;
import com.musairov.shop.utils.DbConnection;
import com.musairov.shop.utils.ExpiredDateProducts;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class Warehouse extends DbConnection {
    private final ProductRepository productRepository;

    public Warehouse(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product, Integer count) {
        count = checkCount(count);
        checkExpiryDate(product);

        Product item = getById(product.getId());

        if (Objects.nonNull(item)) {
            increaseCountProducts(product.getId(), count);
        } else {
            insertProduct(product.getId(), count);
        }
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    private void insertProduct(Integer productId, Integer count) {
        checkCount(count);

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("insert into warehouse (product_id, count) values (?, ?);");
            ps.setInt(1, productId);
            ps.setInt(2, count);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Warehouse Error");
        }

    }

    public Product getById(Integer productId, Integer count) {
        checkCount(count);

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("select * from warehouse where product_id = ?;");
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return productRepository.getById(rs.getInt("product_id"));
            }
        } catch (SQLException e) {
            System.out.println("Warehouse Error");
        }

        throw new NullPointerException();
    }

    public Product getById(Integer id) {
        return getById(id, 1);
    }

    public Integer countItems() {
        int total = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select sum(count) as total from warehouse;");
            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println("Warehouse Error");
        }

        return total;
    }

    public Integer countProducts() {
        int count = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select count(*) as amount from warehouse;");
            if (rs.next()) {
                count = rs.getInt("amount");
            }
        } catch (SQLException e) {
            System.out.println("Warehouse Error");
        }

        return count;
    }

    public Integer countProductById(Integer productId) {
        int count = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement(
                    "select count from warehouse where product_id = ?;"
            );
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("Warehouse Error");
        }

        return count;
    }

    public boolean isEmpty() {
        return countItems() == 0;
    }

    private Integer checkCount(Integer count) {
        return count < 0 ? 0 : count;
    }

    public Map<Product, Integer> getAll() {
        Map<Product, Integer> products = new LinkedHashMap<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("select * from warehouse order by product_id;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = productRepository.getById(rs.getInt("product_id"));
                products.put(product, rs.getInt("count"));
            }
        } catch (SQLException e) {
            System.out.println("Warehouse Error");
        }

        return products;
    }

    public boolean update(Integer productId, Integer count) {
        int rows = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("update warehouse set count = ? where product_id = ?;");
            ps.setInt(1, count);
            ps.setInt(2, productId);
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Warehouse Error");
        }
        return rows != 0;
    }

    public boolean increaseCountProducts(Integer productId, Integer count) {
        int rows = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement(
                    "update warehouse set count = (count + ?) where product_id = ?;"
            );
            ps.setInt(1, count);
            ps.setInt(2, productId);
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Warehouse Error");
        }

        return rows != 0;
    }

    public boolean reduceCountProducts(Integer productId, Integer count) {
        int rows = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement(
                    "update warehouse set count = count - ? where product_id = ?;"
            );
            ps.setInt(1, count);
            ps.setInt(2, productId);
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Warehouse Error");
        }

        return rows != 0;
    }

    private void checkExpiryDate(Product product) {
        if (product instanceof ShelfLifeProduct) {
            try {
                Field expiredDateField = product.getClass().getDeclaredField("expiredDate");
                expiredDateField.setAccessible(true);
                ExpiredDateProducts annotation = expiredDateField.getDeclaredAnnotation(ExpiredDateProducts.class);
                int lifeDays = annotation.shelfLife();
                expiredDateField.set(product, LocalDate.now().plusDays(lifeDays));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException("Expired Date Error");
            }
        }
    }
}
