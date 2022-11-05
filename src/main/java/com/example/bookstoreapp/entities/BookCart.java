package com.example.bookstoreapp.entities;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@SessionScope
@Getter
@Component
public class BookCart {

    private List<BooksDto> booksDtoList = new ArrayList<>();

    private BooksDto bookToBooksDto(Books books) {
        return new BooksDto(
                books.getId(),
                books.getTitle(),
                books.getAuthor(),
                books.getPrice(),
                books.getImgUrl()
        );
    }

    public void addBook(Books books) {

        booksDtoList.add(bookToBooksDto(books));
    }

    public void removeBook(Books books) {
        booksDtoList.remove(bookToBooksDto(books));
    }

    public void clearCart() {
        booksDtoList.clear();
    }

    public List<BooksDto> getBooks() {
        return booksDtoList;
    }

    public int getBookSize() {
        return booksDtoList.size();
    }
}



















