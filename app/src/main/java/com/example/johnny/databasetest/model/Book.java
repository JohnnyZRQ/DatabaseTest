package com.example.johnny.databasetest.model;

/**
 * Created by Johnny on 2016/8/16.
 */

public class Book {
    private int id;
    private String name;
    private String author;
    private int pages;
    private double price;

    public Book(int id,String name,String author,int pages,double price){
        this.id = id;
        this.name = name;
        this.author = author;
        this.pages = pages;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }

    public double getPrice() {
        return price;
    }
}
