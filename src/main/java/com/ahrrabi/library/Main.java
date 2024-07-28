package com.esisa.biblio.main;

import com.ahrrabi.library.dao.AuthorDAO;
import com.ahrrabi.library.dao.AuthorDaoJDBC;
import com.ahrrabi.library.dao.jdbc.Database;
import com.ahrrabi.library.dao.jdbc.PostgreSQLDataSource;
import com.ahrrabi.library.dao.utils.SQLScriptRunner;
import com.ahrrabi.library.dao.models.Author;
import com.ahrrabi.library.dao.service.AuthorService;
import com.ahrrabi.library.dao.service.AuthorServiceImpl;


import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PostgreSQLDataSource dataSource = new PostgreSQLDataSource("localhost", "library", "postgres", "123456789");
        Database db = new Database(dataSource);

        // Execute SQL script to create tables if they do not exist
      /*  try (Connection connection = dataSource.getConnection()) {
            SQLScriptRunner.runScript(connection, "src/main/resources/schema.sql");
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        AuthorDAO authorDAO = new AuthorDaoJDBC(db);
        AuthorService authorService = new AuthorServiceImpl(authorDAO);

        // Insert a new author
        Author author = new Author(4, "J.K. Ahrrabi", 1965);
        authorService.addAuthor(author);

        // Update an existing author
        author.setName("abdennabi ahrrabi");
        authorService.updateAuthor(author);

        // Select an author by ID
        Author selectedAuthor = authorService.getAuthorById(4);
        System.out.println("Selected Author: " + selectedAuthor.getName());

        // Select all authors
        List<Author> authors = authorService.getAllAuthors();
        for (Author a : authors) {
            System.out.println(a.getName());
        }

        // Delete an author
        authorService.deleteAuthor(1);
    }
}