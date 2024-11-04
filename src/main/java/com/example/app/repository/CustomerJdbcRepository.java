package com.example.app.repository;

import com.example.app.core.DataAccessException;
import com.example.app.model.Customer;
import com.example.app.repository.support.JdbcUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerJdbcRepository implements CustomerRepository {

    private static Log log = LogFactory.getLog(CustomerJdbcRepository.class);

    private DataSource dataSource = null;

    public CustomerJdbcRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public BigInteger create(Customer customer) throws DataAccessException {
        String sql = "INSERT INTO customers (name, email) VALUES (?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        BigInteger result = BigInteger.valueOf(0);
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            int numRows = ps.executeUpdate();
            if (numRows > 0) {
                return BigInteger.valueOf(JdbcUtils.getLastInsertId(ps));
            }
        } catch (SQLException ex) {
            throw new DataAccessException("Error saving customer", ex);
        } finally {
            JdbcUtils.closePreparedStatement(ps);
            JdbcUtils.closeConnection(conn);
        }
        return result;
    }

    @Override
    public Customer findById(BigInteger id) throws DataAccessException {
        String sql = "SELECT id, name, email FROM customers WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        Customer customer = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id.longValue());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                customer = new Customer();
                customer.setId(BigInteger.valueOf(rs.getLong("id")));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
            }
        } catch (SQLException ex) {
            throw new DataAccessException("Error finding customer", ex);
        } finally {
            JdbcUtils.closePreparedStatement(ps);
            JdbcUtils.closeConnection(conn);
        }
        return customer;
    }

    @Override
    public Customer findByEmail(String email) throws DataAccessException {
        String sql = "SELECT id, name, email FROM customers WHERE email = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        Customer customer = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                customer = new Customer();
                customer.setId(BigInteger.valueOf(rs.getLong("id")));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
            }
        } catch (SQLException ex) {
            throw new DataAccessException("Error finding customer", ex);
        } finally {
            JdbcUtils.closePreparedStatement(ps);
            JdbcUtils.closeConnection(conn);
        }
        return customer;
    }

    @Override
    public void update(Customer customer) throws DataAccessException {
        String sql = "UPDATE customers SET name = ?, email = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setLong(3, customer.getId().longValue());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new DataAccessException("Error updating customer", ex);
        } finally {
            JdbcUtils.closePreparedStatement(ps);
            JdbcUtils.closeConnection(conn);
        }
    }

    @Override
    public void delete(BigInteger id) throws DataAccessException {
        String sql = "DELETE FROM customers WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id.longValue());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new DataAccessException("Error deleting customer", ex);
        } finally {
            JdbcUtils.closePreparedStatement(ps);
            JdbcUtils.closeConnection(conn);
        }
    }

    @Override
    public List<Customer> findAll() throws DataAccessException {
        String sql = "SELECT id, name, email FROM customers";
        Connection conn = null;
        PreparedStatement ps = null;
        List<Customer> customers = new ArrayList<Customer>();
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Customer customer = new Customer();
                customer.setId(BigInteger.valueOf(rs.getLong("id")));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customers.add(customer);
            }
        } catch (SQLException ex) {
            throw new DataAccessException("Error finding customers", ex);
        } finally {
            JdbcUtils.closePreparedStatement(ps);
            JdbcUtils.closeConnection(conn);
        }
        return customers;
    }

    @Override
    public List<Customer> findByNameLike(String name) throws DataAccessException {
        String sql = "SELECT id, name, email FROM customers WHERE name LIKE ?";
        Connection conn = null;
        PreparedStatement ps = null;
        List<Customer> customers = new ArrayList<Customer>();
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Customer customer = new Customer();
                customer.setId(BigInteger.valueOf(rs.getLong("id")));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customers.add(customer);
            }
        } catch (SQLException ex) {
            throw new DataAccessException("Error finding customers", ex);
        } finally {
            JdbcUtils.closePreparedStatement(ps);
            JdbcUtils.closeConnection(conn);
        }
        return customers;
    }

    @Override
    public List<Customer> findByNameWithPaging(String name, int offset, int pageSize) throws DataAccessException {
        String sql = "SELECT id, name, email FROM customers WHERE name LIKE ? LIMIT ? OFFSET ?";
        Connection conn = null;
        PreparedStatement ps = null;
        List<Customer> customers = new ArrayList<Customer>();
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ps.setInt(2,  pageSize);
            ps.setInt(3, offset);
            log.debug("SQL: " + ps.toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Customer customer = new Customer();
                customer.setId(BigInteger.valueOf(rs.getLong("id")));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customers.add(customer);
            }
        } catch (SQLException ex) {
            throw new DataAccessException("Error finding customers", ex);
        } finally {
            JdbcUtils.closePreparedStatement(ps);
            JdbcUtils.closeConnection(conn);
        }
        return customers;
    }


}
