package com.example.app.repository;

import com.example.app.core.DataAccessException;
import com.example.app.model.Customer;
import com.example.app.repository.support.JdbcUtils;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerJdbcRepository implements CustomerRepository {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public BigInteger save(Customer customer) throws DataAccessException {
        String sql = "INSERT INTO customer (name, email) VALUES (?, ?)";
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
}
