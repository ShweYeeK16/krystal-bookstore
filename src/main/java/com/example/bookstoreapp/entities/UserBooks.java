package com.example.bookstoreapp.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class UserBooks{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Users users;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Books> booksList = new ArrayList<>();

    public void addBooks(Books books) {
        booksList.add(books);
    }
}
