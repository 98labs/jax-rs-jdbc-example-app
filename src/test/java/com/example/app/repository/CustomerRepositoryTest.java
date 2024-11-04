package com.example.app.repository;

import com.example.app.config.ApplicationConfig;
import com.example.app.model.Customer;
import junit.framework.TestCase;

import java.math.BigInteger;
import java.util.List;

public class CustomerRepositoryTest extends TestCase {

    ApplicationConfig appConfig;
    CustomerJdbcRepository repository;

    public void setUp() throws Exception {
        super.setUp();
        appConfig = new ApplicationConfig();
        repository = new CustomerJdbcRepository();
        repository.setDataSource(appConfig.getDataSource());
    }

    public void tearDown() throws Exception {
        appConfig.destroy();
    }

//    public void testCreate() {
//    }

    public void testFindById() {
        Customer customer = repository.findById(BigInteger.valueOf(1));
        assertNotNull(customer);
        assertEquals("Alice Johnson", customer.getName());
        assertEquals("alice.johnson@example.com", customer.getEmail());
    }

//    public void testFindByEmail() {
//    }
//
//    public void testUpdate() {
//    }
//
//    public void testDelete() {
//    }
//
//    public void testFindAll() {
//    }
//
//    public void testFindByNameLike() {
//    }

    public void testFindByNameWithPaging() {
        List<Customer> customers = repository.findByNameWithPaging("a", 6, 2);
        assertNotNull(customers);
        assertEquals(2, customers.size());
        Customer customer = null;
        customer = customers.get(0);
        assertEquals(9, Long.parseLong(customer.getId().toString()));
        assertEquals("Ivy Taylor", customer.getName());
        customer = customers.get(1);
        assertEquals(10, Long.parseLong(customer.getId().toString()));
        assertEquals("Jack Anderson", customer.getName());
    }
}