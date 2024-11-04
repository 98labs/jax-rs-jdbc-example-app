package com.example.app.repository.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class JdbcUtils {

    private static final Log log = LogFactory.getLog(JdbcUtils.class);

    /**
     * Close the given JDBC Connection and ignore any thrown exception.
     * This is useful for typical finally blocks in manual JDBC code.
     *
     * @param con the JDBC Connection to close (may be {@code null})
     */
    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                log.debug("Could not close JDBC Connection", ex);
            } catch (Throwable ex) {
                // We don't trust the JDBC driver: It might throw RuntimeException or Error.
                log.debug("Unexpected exception on closing JDBC Connection", ex);
            }
        }
    }

    /**
     * Close the given JDBC PreparedStatement and ignore any thrown exception.
     * This is useful for typical finally blocks in manual JDBC code.
     *
     * @param ps the JDBC Statement to close (may be {@code null})
     */
    public static void closePreparedStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException ex) {
                log.trace("Could not close JDBC Statement", ex);
            } catch (Throwable ex) {
                // We don't trust the JDBC driver: It might throw RuntimeException or Error.
                log.trace("Unexpected exception on closing JDBC Statement", ex);
            }
        }
    }

    /**
     * Close the given JDBC Statement and ignore any thrown exception.
     * This is useful for typical finally blocks in manual JDBC code.
     *
     * @param stmt the JDBC Statement to close (may be {@code null})
     */
    public static void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                log.trace("Could not close JDBC Statement", ex);
            } catch (Throwable ex) {
                // We don't trust the JDBC driver: It might throw RuntimeException or Error.
                log.trace("Unexpected exception on closing JDBC Statement", ex);
            }
        }
    }

    /**
     * Close the given JDBC ResultSet and ignore any thrown exception.
     * This is useful for typical finally blocks in manual JDBC code.
     *
     * @param rs the JDBC ResultSet to close (may be {@code null})
     */
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                log.trace("Could not close JDBC ResultSet", ex);
            } catch (Throwable ex) {
                // We don't trust the JDBC driver: It might throw RuntimeException or Error.
                log.trace("Unexpected exception on closing JDBC ResultSet", ex);
            }
        }
    }

    /**
     * Retrieve the value of the auto-increment column for the last row that was inserted.
     *
     * @param ps the PreparedStatement used to perform the insert
     * @return the value of the auto-increment column
     * @throws SQLException if the auto-increment column could not be retrieved
     */
    public static long getLastInsertId(PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.getGeneratedKeys();
        long result = 0;
        if (rs.last()) {
            result = rs.getLong(1);
        }
        return result;
    }

    /**
     * Retrieve the values of the auto-increment column for the rows that were inserted.
     *
     * @param ps the PreparedStatement used to perform the insert
     * @return the values of the auto-increment column
     * @throws SQLException if the auto-increment column could not be retrieved
     */
    public static List<Long> getInsertIds(PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.getGeneratedKeys();
        List<Long> result = new ArrayList<>();
        while (rs.last()) {
            result.add(rs.getLong(1));
        }
        return result;
    }

}
