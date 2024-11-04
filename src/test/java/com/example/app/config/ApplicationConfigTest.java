package com.example.app.config;

import com.example.app.config.properties.YamlConfig;
import junit.framework.TestCase;

public class ApplicationConfigTest extends TestCase {

    public void testLoadYamlConfig() {
        YamlConfig config = new ApplicationConfig().loadYamlConfig();

//        assertEquals("jdbc:oracle:thin:@localhost:1521:xe", config.getDatabase().getConnection().getJdbcUrl());
//        assertEquals("username", config.getDatabase().getConnection().getUsername());
//        assertEquals("password", config.getDatabase().getConnection().getPassword());
//        assertEquals("oracle.jdbc.OracleDriver", config.getDatabase().getConnection().getDriverClassName());
        assertEquals(10, config.getDatabase().getConnectionPool().getMaximumPoolSize());
        assertEquals(2, config.getDatabase().getConnectionPool().getMinimumIdle());
        assertEquals(30000, config.getDatabase().getConnectionPool().getIdleTimeout());
        assertEquals(20000, config.getDatabase().getConnectionPool().getConnectionTimeout());
        assertEquals(1800000, config.getDatabase().getConnectionPool().getMaxLifetime());
    }
}