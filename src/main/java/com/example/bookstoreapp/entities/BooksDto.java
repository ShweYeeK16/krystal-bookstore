package com.example.bookstoreapp.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class BooksDto {

    private int id;
    private String title;
    private String author;
    private double price;
    private String imgUrl;

    public BooksDto() {

    }

    public BooksDto(int id, String title, String author, double price, String imgUrl) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.imgUrl = imgUrl;
    }
}
















