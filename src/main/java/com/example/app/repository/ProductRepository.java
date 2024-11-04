package com.example.app.repository;

import com.example.app.core.DataAccessException;
import com.example.app.model.Product;

import java.math.BigInteger;

public interface ProductRepository {

    public BigInteger save(Product product) throws DataAccessException;
}
