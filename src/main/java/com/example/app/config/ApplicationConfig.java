package com.example.app.config;

import com.example.app.config.properties.Connection;
import com.example.app.config.properties.ConnectionPool;
import com.example.app.config.properties.Database;
import com.example.app.config.properties.YamlConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import java.io.InputStream;

@ApplicationScoped
public class ApplicationConfig {
    private HikariDataSource dataSource;

    @PostConstruct
    public void init() {
        // load configuration from YAML
        YamlConfig yamlConfig = loadYamlConfig();
        Database database = yamlConfig.getDatabase();

        // load database connection settings
        HikariConfig config = new HikariConfig();
        Connection connection = database.getConnection();
        config.setJdbcUrl(connection.getJdbcUrl());
        config.setUsername(connection.getUsername());
        config.setPassword(connection.getPassword());
        config.setDriverClassName(connection.getDriverClassName());

        // load HikariCP specific settings
        ConnectionPool connectionPool = database.getConnectionPool();
        config.setMaximumPoolSize(connectionPool.getMaximumPoolSize());
        config.setMinimumIdle(connectionPool.getMinimumIdle());
        config.setIdleTimeout(connectionPool.getIdleTimeout());
        config.setConnectionTimeout(connectionPool.getConnectionTimeout());
        config.setMaxLifetime(connectionPool.getMaxLifetime());

        // create a new DataSource
        this.dataSource = new HikariDataSource(config);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @PreDestroy
    public void close() {
        if (dataSource != null) {
            dataSource.close();
        }
    }

    YamlConfig loadYamlConfig() {
        // Load configuration from YAML
        Yaml yaml = new Yaml(new Constructor(YamlConfig.class, new LoaderOptions()));
        System.out.println(this.getClass()
                .getClassLoader().toString());
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("application.yml");
        return yaml.load(inputStream);
    }

}
