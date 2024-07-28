package com.ahrrabi.library.dao.jdbc;

import java.sql.*;


public class Database {
    private DataSource ds;
    private Connection db = null;

    public Database() {}

    public Database(DataSource ds) {
        setDataSource(ds);
    }

    public void setDataSource(DataSource ds) {
        this.ds = ds;
        this.db = ds.getConnection();
    }

    public DataSource getDataSource() {
        return ds;
    }

    public String[][] select(String tableName) {
        return executeSelect("SELECT * FROM " + tableName);
    }

    public String[][] select(String tableName, String key, Object value) {
        return executeSelect("SELECT * FROM " + tableName + " WHERE " + key + " = '" + value + "'");
    }

    public String[][] selectContains(String tableName, String key, String keyword) {
        return executeSelect("SELECT * FROM " + tableName + " WHERE " + key + " LIKE '%" + keyword + "%'");
    }

    public String[][] selectStartsWith(String tableName, String key, String keyword) {
        return executeSelect("SELECT * FROM " + tableName + " WHERE " + key + " LIKE '" + keyword + "%'");
    }

    public int insert(String tableName, Object... row) {
        String query = "INSERT INTO " + tableName + " VALUES('" + row[0] + "'";
        for (int i = 1; i < row.length; i++) {
            query = query + ", '" + row[i] + "'";
        }
        query = query + ")";
        return executeUpdate(query);
    }

    public int update(String tableName, Object... row) {
        if (row.length < 2) return -1;
        Field[] fields = getFields(tableName);
        if (row.length > fields.length) return -1;
        String query = "UPDATE " + tableName + " SET " + fields[1].getName() + " = '" + row[1] + "'";
        for (int i = 2; i < row.length; i++) {
            query = query + ", " + fields[i].getName() + " = '" + row[i] + "'";
        }
        query = query + " WHERE " + fields[0].getName() + " = '" + row[0] + "'";
        return executeUpdate(query);
    }

    public int delete(String tableName, String key, Object value) {
        String query = "DELETE FROM " + tableName + " WHERE " + key + " = '" + value + "'";
        return executeUpdate(query);
    }

    public String[][] executeSelect(String query) {
        try (Statement sql = db.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = sql.executeQuery(query);
            ResultSetMetaData rsm = rs.getMetaData();
            int cols = rsm.getColumnCount();
            rs.last();
            int rows = rs.getRow();
            rs.beforeFirst();
            String[][] data = new String[rows + 1][cols];
            for (int col = 0; col < cols; col++) {
                data[0][col] = rsm.getColumnName(col + 1);
            }
            int row = 0;
            while (rs.next()) {
                row++;
                for (int col = 0; col < cols; col++) {
                    data[row][col] = rs.getString(col + 1);
                }
            }
            return data;
        } catch (SQLException e) {
            com.ahrrabi.library.dao.jdbc.GlobalExceptionHandler.handleException(e);
            return null;
        }
    }

    public Field[] getFields(String tableName) {
        try (Statement sql = db.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = sql.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData rsm = rs.getMetaData();
            int cols = rsm.getColumnCount();
            Field[] fields = new Field[cols];
            for (int col = 0; col < cols; col++) {
                fields[col] = new Field(rsm.getColumnName(col + 1), rsm.getColumnType(col + 1), rsm.getColumnDisplaySize(col + 1));
            }
            return fields;
        } catch (SQLException e) {
            com.ahrrabi.library.dao.jdbc.GlobalExceptionHandler.handleException(e);
            return null;
        }
    }

    public int executeUpdate(String query) {
        try (Statement sql = db.createStatement()) {
            return sql.executeUpdate(query);
        } catch (SQLException e) {
            com.ahrrabi.library.dao.jdbc.GlobalExceptionHandler.handleException(e);
            return -1;
        }
    }
}