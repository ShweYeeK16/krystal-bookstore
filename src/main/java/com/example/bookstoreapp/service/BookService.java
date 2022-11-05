package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dao.BookDao;
import com.example.bookstoreapp.dao.CategoryDao;
import com.example.bookstoreapp.entities.Books;
import com.example.bookstoreapp.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private CategoryDao categoryDao;

    public BookService(BookDao bookDao, CategoryDao categoryDao) {
        this.bookDao = bookDao;
        this.categoryDao = categoryDao;
    }

    @Transactional
    public Books findBooks(int id){
        return bookDao.getById(id);
    }

    @Transactional
    public List<Books> findAllBooks(){
        return bookDao.findAll();
    }

    public Books saveBook(int catId, Books books) {
        Category category = categoryDao.getById(catId);

        return bookDao.save(category.addCourse(books));
    }

    @Transactional
    public void removeBooks(int bookId){

        Books books = findBooks(bookId);
        bookDao.getById(bookId);
        bookDao.delete(books);
    }

    @Transactional
    public Books updateBooks(int bookId, Books newBook, int catId) {

        Books oldBooks = findBooks(bookId);
        Category category = categoryDao.getById(catId);

        oldBooks.setTitle(newBook.getTitle());
        oldBooks.setAuthor(newBook.getAuthor());
        oldBooks.setPrice(newBook.getPrice());
        oldBooks.setImgUrl(newBook.getImgUrl());

        category.addCourse(oldBooks);

        return oldBooks;
    }
}

















