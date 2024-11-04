package com.example.app.repository;

import com.example.app.core.DataAccessException;
import com.example.app.model.Customer;

import java.math.BigInteger;
import java.util.List;

public interface CustomerRepository {

    public BigInteger create(Customer customer) throws DataAccessException;

    public Customer findById(BigInteger id) throws DataAccessException;

    public Customer findByEmail(String email) throws DataAccessException;

    public void update(Customer customer) throws DataAccessException;

    public void delete(BigInteger id) throws DataAccessException;

    public List<Customer> findAll() throws DataAccessException;

    public List<Customer> findByNameLike(String name) throws DataAccessException;

    public List<Customer> findByNameWithPaging(String name, int offset, int pageSize) throws DataAccessException;

}
