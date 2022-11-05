package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.entities.Users;
import com.example.bookstoreapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register", "user", new Users());
    }

    /*public String processRegister(@ModelAttribute("user") Users user, BindingResult result) {

        if(result.hasErrors()) {
            return "account/register";
        }
        else{
            userService.checkOut(user);
        }
    }*/
}





















