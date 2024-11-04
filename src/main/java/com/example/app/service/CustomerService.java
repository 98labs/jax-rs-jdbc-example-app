package com.example.app.service;

import com.example.app.config.ApplicationConfig;
import com.example.app.model.Customer;
import com.example.app.repository.CustomerJdbcRepository;
import com.example.app.repository.CustomerRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public class CustomerService {

    ApplicationConfig config = ApplicationConfig.getInstance();
    CustomerRepository customerRepository = new CustomerJdbcRepository(config.getDataSource());

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(BigInteger id) {
        return Optional.ofNullable(customerRepository.findById(id));
    }

    public BigInteger createCustomer(Customer customer) {
        return customerRepository.create(customer);
    }

    public void updateCustomer(BigInteger id, Customer customer) {
        customerRepository.update(customer);
    }

    public void deleteCustomer(BigInteger id) {
        customerRepository.delete(id);
    }

    public List<Customer> findByNameWithPaging(String name, int page, int size) {
        return customerRepository.findByNameWithPaging(name, page, size);
    }

    public List<Customer> findByNameLike(String name) {
        return customerRepository.findByNameLike(name);
    }

    public Optional<Customer> findByEmail(String email) {
        return Optional.ofNullable(customerRepository.findByEmail(email));
    }

}
