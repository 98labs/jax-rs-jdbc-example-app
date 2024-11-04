package com.example.app.repository;

import com.example.app.core.DataAccessException;
import com.example.app.model.Customer;

import java.math.BigInteger;

public interface CustomerRepository {

    public BigInteger save(Customer customer) throws DataAccessException;

}
