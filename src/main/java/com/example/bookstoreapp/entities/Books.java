package com.example.bookstoreapp.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Books extends IdClass{

    private String title;
    private String author;
    private double price;
    private String imgUrl;

    @ManyToOne
    private Category category;
}
