package com.ahrrabi.library.dao.utils;


import com.ahrrabi.library.dao.models.Author;

public class Mapper {
    public static Author getAuthor(String[] data) {
        return new Author(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]));
    }
}
