package com.ahrrabi.library.dao;

import com.ahrrabi.library.dao.models.Author;

import java.util.List;

public interface AuthorDAO {
    int insert(Author a);
    int update(Author a);
    int delete(int id);
    Author select(int id);
    List<Author> select();
    List<Author> select(String keyword);
}
