package com.ahrrabi.library.dao.service;

import java.util.List;

import com.ahrrabi.library.dao.AuthorDAO;
import com.ahrrabi.library.dao.models.Author;


public class AuthorServiceImpl implements AuthorService {
    private AuthorDAO authorDAO;

    public AuthorServiceImpl(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    @Override
    public void addAuthor(Author author) {
        authorDAO.insert(author);
    }

    @Override
    public void updateAuthor(Author author) {
        authorDAO.update(author);
    }

    @Override
    public void deleteAuthor(int id) {
        authorDAO.delete(id);
    }

    @Override
    public Author getAuthorById(int id) {
        return authorDAO.select(id);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorDAO.select();
    }
}