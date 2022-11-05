package com.example.bookstoreapp.service;

import com.example.bookstoreapp.entities.BookCart;
import com.example.bookstoreapp.entities.Books;
import com.example.bookstoreapp.entities.BooksDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private BookCart bookCart;

    public int cartsize = 0 ;

    public void addToCart(Books books) {
        bookCart.addBook(books);
        updateCartSize();
    }

    public void updateCartSize() {
        cartsize = bookCart.getBooks().size();
    }

    public List<BooksDto> allBooksInCart() {
        return bookCart.getBooks();
    }

    public void clearCart() {
        bookCart.clearCart();
        updateCartSize();
    }

    public void removeBookFromCart(Books books) {
        bookCart.removeBook(books);
        updateCartSize();
    }
}

























