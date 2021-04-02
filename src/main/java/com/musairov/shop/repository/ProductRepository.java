package com.musairov.shop.repository;

import com.musairov.shop.dao.Product;
import com.musairov.shop.dao.ProductGroup;
import com.musairov.shop.utils.DbConnection;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ProductRepository extends DbConnection {
    public List<Product> products = new ArrayList<>();

    public ProductRepository() {
    }

    public boolean create(String name, BigDecimal price, ProductGroup productGroup) {
        int rows = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("insert into products (name, price, group ) values (?, ?, ?);");
            ps.setString(1, name);
            ps.setBigDecimal(2, price);
            ps.setString(3, productGroup.name());
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Product Error");
        }

        return rows != 0;
    }

    public Product getById(Integer id) {
        Product product = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("select * from products where id = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        ProductGroup.valueOf(rs.getString("group")),
                        rs.getBigDecimal("price")
                );
            }
        } catch (SQLException e) {
            System.out.println("Product Error");
        }

        return product;
    }

    public List<Product> getAll() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("select * from products");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        ProductGroup.valueOf(rs.getString("group")),
                        rs.getBigDecimal("price")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Product Error");
        }

        return products;
    }
}
