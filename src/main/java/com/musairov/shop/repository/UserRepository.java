package com.musairov.shop.repository;

import com.musairov.shop.dao.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    public User getByLoginAndPassword(String login, String password) {
        User user = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            String hash = DigestUtils.sha512_224Hex(password);
            user = jdbcTemplate.queryForObject(
                    "select * from customers where login = ? and password = ?",
                    new Object[]{login, hash},
                    (rs, i) -> new User(UUID.fromString(rs.getString("id")), login, hash)
            );
            connection.commit();
        } catch (Exception e) {
            System.out.println("User Exception");
            try {
                if (Objects.nonNull(connection)) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Rollback Exception");
            }
        }

        return user;
    }

    public User create(String login, String password) {
        User user = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            int rows = jdbcTemplate.update(
                    "insert into customers (id, login, password) values (?, ?, ?)",
                    UUID.randomUUID().toString(),
                    login,
                    DigestUtils.sha512_224Hex(password)
            );

            if (rows != 0) {
                user = getByLoginAndPassword(login, password);
            }
            connection.commit();
        } catch (Exception e) {
            System.out.println("User Exception");
            try {
                if (Objects.nonNull(connection)) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Rollback Exception");
            }
        }

        return user;
    }
}
