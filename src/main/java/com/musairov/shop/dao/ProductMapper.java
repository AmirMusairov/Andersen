package com.musairov.shop.dao;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int i) throws SQLException {
        return new Product(
                rs.getInt("id"),
                rs.getString("name"),
                ProductGroup.valueOf(rs.getString("group")),
                rs.getBigDecimal("price")
        );
    }
}
