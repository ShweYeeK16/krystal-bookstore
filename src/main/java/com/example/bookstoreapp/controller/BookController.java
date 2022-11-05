package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.entities.Books;
import com.example.bookstoreapp.entities.Category;
import com.example.bookstoreapp.service.BookService;
import com.example.bookstoreapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    private int bookId;

    @GetMapping({"/", "/home"})
    public String index(){
        return "index";
    }

    @GetMapping("/admin/all-books")
    public String allBooks(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return "admin/all-books";
    }

    @GetMapping("/admin/create-book")
    public String create(Model model) {
        model.addAttribute("book", new Books());
        model.addAttribute("categories", categoryService.findAllCategory());

        return "admin/book-form";
    }

    @PostMapping("/admin/create-book")
    public String save(@Valid Books books, @RequestParam("catId") int catId, BindingResult result) {

        if (result.hasErrors()) {
            return "admin/book-form";
        }
        else {
            bookService.saveBook(catId, books);
        }

        return "redirect:/admin/all-books";
    }

    @GetMapping("/admin/all-books/delete/{bookId}")
    public String deleteBook(@PathVariable int bookId) {
        bookService.removeBooks(bookId);

        return "redirect:/admin/all-books";
    }

    @GetMapping("/admin/all-books/update")
    public String updateBook(@RequestParam("bookId") int bookId, Model model) {
        model.addAttribute("categories", categoryService.findAllCategory());
        model.addAttribute("book", new Books());

        this.bookId = bookId;

        return "admin/course-update";
    }

    @PostMapping("/admin/all-books/process")
    public String updateProcess(@RequestParam("catId") int catId, Books books, BindingResult result) {

        if(result.hasErrors()) {
            return "admin/course-update";
        }
        else {
            bookService.updateBooks(bookId, books, catId);
        }

        return "redirect:/admin/all-books";
    }

}


























