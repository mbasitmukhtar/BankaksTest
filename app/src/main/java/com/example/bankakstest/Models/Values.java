package com.example.bankakstest.Models;

public class Values {
    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Values(String name, int id) {
        this.name = name;
        this.id = id;
    }
}
