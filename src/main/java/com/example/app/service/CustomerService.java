package com.example.app.service;

import com.example.app.model.Customer;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class CustomerService {

    private static final Map<BigInteger, Customer> customers = new HashMap<>();
    private static final AtomicLong idCounter = new AtomicLong();

    public List<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        customers.add(Customer.builder().id(BigInteger.valueOf(1)).name("John Doe").email("john.doe@example.com").build());
        customers.add(Customer.builder().id(BigInteger.valueOf(2)).name("Jane Doe").email("jane.doe@example.com").build());
        return customers;
    }

    public Optional<Customer> getCustomerById(BigInteger id) {
        return Optional.ofNullable(customers.get(id));
    }

    public Customer createCustomer(Customer customer) {
        BigInteger id = BigInteger.valueOf(idCounter.incrementAndGet());
        customer.setId(id);
        customers.put(id, customer);
        return customer;
    }

    public Optional<Customer> updateCustomer(BigInteger id, Customer updatedCustomer) {
        Customer existingCustomer = customers.get(id);
        if (existingCustomer != null) {
            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setOrders(updatedCustomer.getOrders());
            return Optional.of(existingCustomer);
        }
        return Optional.empty();
    }

    public boolean deleteCustomer(BigInteger id) {
        return customers.remove(id) != null;
    }

    public List<Customer> findByNameWithPaging(String name, int page, int size) {
        return customers.values().stream()
                .filter(customer -> customer.getName().equalsIgnoreCase(name))
                .skip((page - 1) * size)
                .limit(size)
                .collect(Collectors.toList());
    }

    public List<Customer> findByNameLike(String name) {
        return customers.values().stream()
                .filter(customer -> customer.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Optional<Customer> findByEmail(String email) {
        return customers.values().stream()
                .filter(customer -> customer.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

}
