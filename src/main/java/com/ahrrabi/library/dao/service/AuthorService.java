package com.ahrrabi.library.dao.service;

import com.ahrrabi.library.dao.models.Author;

import java.util.List;

public interface AuthorService {
    void addAuthor(Author author);
    void updateAuthor(Author author);
    void deleteAuthor(int id);
    Author getAuthorById(int id);
    List<Author> getAllAuthors();
}
