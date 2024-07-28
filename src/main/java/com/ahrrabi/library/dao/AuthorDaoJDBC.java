package com.ahrrabi.library.dao;

import com.ahrrabi.library.dao.jdbc.Database;
import com.ahrrabi.library.dao.models.Author;
import com.ahrrabi.library.dao.utils.Mapper;

import java.util.List;
import java.util.Vector;

public class AuthorDaoJDBC implements AuthorDAO {
    private Database db;
    private String tableName = "Authors";

    public AuthorDaoJDBC(Database db) {
        this.db = db;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    @Override
    public int insert(Author a) {
        return db.insert(tableName, a.getId(), a.getName(), a.getYearBorn()) > 0 ? 1 : -1;
    }

    @Override
    public int update(Author a) {
        return db.update(tableName, a.getId(), a.getName(), a.getYearBorn());
    }

    @Override
    public int delete(int id) {
        return db.delete(tableName, "id", id);
    }

    @Override
    public Author select(int id) {
        String[][] data = db.select(tableName, "id", id);
        return data.length > 1 ? Mapper.getAuthor(data[1]) : null;
    }

    @Override
    public List<Author> select() {
        String[][] data = db.select(tableName);
        Vector<Author> authors = new Vector<>();
        for (int row = 1; row < data.length; row++) {
            authors.add(Mapper.getAuthor(data[row]));
        }
        return authors;
    }

    @Override
    public List<Author> select(String keyword) {
        String[][] data = db.selectContains(tableName, "name", keyword);
        Vector<Author> authors = new Vector<>();
        for (int row = 1; row < data.length; row++) {
            authors.add(Mapper.getAuthor(data[row]));
        }
        return authors;
    }
}
