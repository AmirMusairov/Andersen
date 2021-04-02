package com.musairov.shop.repository;

import com.musairov.shop.dao.Order;
import com.musairov.shop.dao.User;
import com.musairov.shop.utils.DbConnection;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
public class OrderRepository extends DbConnection {

    public boolean create(UUID userId, BigDecimal total, Boolean accepted) {
        int rows = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("insert into orders (user_id, created_at, total, accepted) values (?, ?, ?, ?)");
            ps.setString(1, userId.toString());
            ps.setDate(2, Date.valueOf(LocalDate.now()));
            ps.setBigDecimal(3, total);
            ps.setBoolean(4, accepted);
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Order Error");
        }

        return rows != 0;
    }

    public List<Order> getAll(User user) {
        List<Order> orders = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            CallableStatement cs = connection.prepareCall("{call get_orders(?)}");
            cs.setString(1, user.getId().toString());
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                orders.add(new Order(rs.getInt("id"),
                        rs.getDate("created_at").toLocalDate(),
                        UUID.fromString(rs.getString("user_id")),
                        rs.getBigDecimal("total"),
                        rs.getBoolean("accepted")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Order Error");
        }

        return orders;
    }

    public Order getById(User user, Integer orderId) {
        Order order = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("select * from orders where id = ? and user_id = ?");
            ps.setInt(1, orderId);
            ps.setString(2, user.getId().toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                order = new Order(
                        rs.getInt("id"),
                        rs.getDate("created_at").toLocalDate(),
                        UUID.fromString(rs.getString("user_id")),
                        rs.getBigDecimal("total"),
                        rs.getBoolean("accepted")
                );
            }
        } catch (SQLException e) {
            System.out.println("Order Error");
        }

        return order;
    }

    public boolean acceptOrder(User user, Integer orderId) {
        int rows = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("update orders set accepted = true where id = ? and user_id = ?");
            ps.setInt(1, orderId);
            ps.setString(2, user.getId().toString());
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Order Error");
        }

        return rows != 0;
    }

    public List<Order> getNotAcceptedOrders(User user) {
        List<Order> orders = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("select * from orders where accepted = false and user_id = ?");
            ps.setString(1, user.getId().toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("id"),
                        rs.getDate("created_at").toLocalDate(),
                        UUID.fromString(rs.getString("user_id")),
                        rs.getBigDecimal("total"),
                        rs.getBoolean("accepted")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Order Error");
        }

        return orders;
    }
}
