package com.ahrrabi.library.dao.jdbc;

public class Field {
    private String name;
    private int type;
    private int size;

    public Field() {
    }


    public Field(String name, int type, int size) {
        this.name = name;
        this.type = type;
        this.size = size;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    @Override
    public String toString() {
        return  "Field [name = " + name + ", type = " + type + ", size = " + size+ "]";
    }
}

