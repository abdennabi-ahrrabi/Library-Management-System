package com.esisa.dao;

import com.ahrrabi.library.dao.AuthorDAO;
import com.ahrrabi.library.dao.AuthorDaoJDBC;
import com.ahrrabi.library.dao.jdbc.Database;
import com.ahrrabi.library.dao.jdbc.MySQLDataSource;
import com.ahrrabi.library.dao.models.Author;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthorDaoJDBCTest {

    private AuthorDAO authorDAO;
    private Database db;

    @BeforeEach
    void setUp() {
        MySQLDataSource dataSource = new MySQLDataSource("library", "root", "password");
        db = new Database(dataSource);
        authorDAO = new AuthorDaoJDBC(db);
    }

    @Test
    void testInsertAndSelect() {
        Author author = new Author(1, "J.K. Rowling", 1965);
        int result = authorDAO.insert(author);
        assertEquals(1, result);

        Author selectedAuthor = authorDAO.select(1);
        assertNotNull(selectedAuthor);
        assertEquals("J.K. Rowling", selectedAuthor.getName());
    }

    @Test
    void testUpdate() {
        Author author = new Author(1, "J.K. Rowling", 1965);
        authorDAO.insert(author);

        author.setName("Joanne Rowling");
        authorDAO.update(author);

        Author updatedAuthor = authorDAO.select(1);
        assertEquals("Joanne Rowling", updatedAuthor.getName());
    }

    @Test
    void testDelete() {
        Author author = new Author(1, "J.K. Rowling", 1965);
        authorDAO.insert(author);

        authorDAO.delete(1);
        Author deletedAuthor = authorDAO.select(1);
        assertNull(deletedAuthor);
    }

    @Test
    void testSelectAll() {
        Author author1 = new Author(1, "J.K. Rowling", 1965);
        Author author2 = new Author(2, "George R.R. Martin", 1948);
        authorDAO.insert(author1);
        authorDAO.insert(author2);

        List<Author> authors = authorDAO.select();
        assertEquals(2, authors.size());
    }
}