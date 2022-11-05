package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.entities.Users;
import com.example.bookstoreapp.service.BookService;
import com.example.bookstoreapp.service.CartService;
import com.example.bookstoreapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("/user/all-books")
    public String allBooks(Model model) {
        model.addAttribute("books", bookService.findAllBooks());

        return "user/allBooks";
    }

    @GetMapping("/user/book-details")
    public String bookDetails(@RequestParam("bookId") int bookId, Model model) {
        model.addAttribute("book", bookService.findBooks(bookId));
        model.addAttribute("cartSize", cartService.cartsize);

        return "user/book-details";
    }

    @GetMapping("/user/add/cart")
    public String addToCart(@RequestParam("bookId") int bookId, Model model,
                            RedirectAttributes attributes) {
        cartService.addToCart(bookService.findBooks(bookId));

        return "redirect:/user/book-details?bookId=" + bookId;
    }

    @GetMapping("/user/cart/view")
    public String cartView(Model model) {
        model.addAttribute("books", cartService.allBooksInCart());

        return "user/cart-view";
    }

    @GetMapping("/user/cart/clear")
    public String clearCart() {
        cartService.clearCart();

        return "redirect:/user/cart/view";
    }

    @GetMapping("/user/cart/remove")
    public String removeCourseFromChart(@RequestParam("bookId") int bookId) {
        cartService.removeBookFromCart(bookService.findBooks(bookId));
        return "redirect:/user/cart/view";
    }

    @GetMapping("/user/cart/checkout")
    public String checkOut(Model model) {
        model.addAttribute("user", new Users());

        return "user/user-form";
    }

    @PostMapping("/user/cart/checkout")
    public String checkOutProcess(Users users, BindingResult result) {

        if(result.hasErrors()) {
            return "user/user-form";
        }
        else{
            userService.checkOut(users, cartService.allBooksInCart());
        }

        return "redirect:/user/all-books";
    }
}



















