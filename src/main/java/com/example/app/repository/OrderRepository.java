package com.example.app.repository;

import com.example.app.core.DataAccessException;
import com.example.app.model.Order;

import java.math.BigInteger;
import java.sql.SQLException;

public interface OrderRepository {

    public BigInteger save(Order order) throws SQLException;

    public void addProductToOrder(BigInteger orderId, BigInteger productId, int quantity) throws DataAccessException;
}
