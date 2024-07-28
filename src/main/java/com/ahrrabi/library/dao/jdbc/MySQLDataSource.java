package com.ahrrabi.library.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLDataSource extends DataSource {
    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String MYSQL_BRIDGE = "jdbc:mysql:";

    public MySQLDataSource(String host, String source, String user, String password) {
        super(MYSQL_DRIVER, MYSQL_BRIDGE, host, source, user, password);
    }

    public MySQLDataSource(String source, String user, String password) {
        super(MYSQL_DRIVER, MYSQL_BRIDGE, "localhost", source, user, password);
    }

    public MySQLDataSource(String source, String user) {
        super(MYSQL_DRIVER, MYSQL_BRIDGE, "localhost", source, user, "");
    }

    public MySQLDataSource(String source) {
        super(MYSQL_DRIVER, MYSQL_BRIDGE, "localhost", source, "root", "");
    }

    @Override
    public Connection getConnection() {
        try {
            Class.forName(MYSQL_DRIVER);
            String url = MYSQL_BRIDGE + "//" + getHost() + "/" + getSource();
            Connection db = DriverManager.getConnection(url, getUser(), getPassword());
            System.out.println("Connexion bien établie...");
            return db;
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
            return null;
        }
    }
}
