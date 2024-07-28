package com.ahrrabi.library.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLDataSource extends DataSource {
    private static final String POSTGRESQL_DRIVER = "org.postgresql.Driver";
    private static final String POSTGRESQL_BRIDGE = "jdbc:postgresql:";

    public PostgreSQLDataSource(String host, String source, String user, String password) {
        super(POSTGRESQL_DRIVER, POSTGRESQL_BRIDGE, host, source, user, password);
    }

    public PostgreSQLDataSource(String source, String user, String password) {
        super(POSTGRESQL_DRIVER, POSTGRESQL_BRIDGE, "localhost", source, user, password);
    }

    public PostgreSQLDataSource(String source, String user) {
        super(POSTGRESQL_DRIVER, POSTGRESQL_BRIDGE, "localhost", source, user, "");
    }

    public PostgreSQLDataSource(String source) {
        super(POSTGRESQL_DRIVER, POSTGRESQL_BRIDGE, "localhost", source, "postgres", "");
    }

    @Override
    public Connection getConnection() {
        try {
            Class.forName(POSTGRESQL_DRIVER);
            String url = POSTGRESQL_BRIDGE + "//" + getHost() + "/" + getSource();
            Connection db = DriverManager.getConnection(url, getUser(), getPassword());
            System.out.println("Connection established successfully...");
            return db;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
