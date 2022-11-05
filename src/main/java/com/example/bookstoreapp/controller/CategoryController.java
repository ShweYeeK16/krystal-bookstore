package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.entities.Category;
import com.example.bookstoreapp.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/admin/all-categories")
    public String showAll(Model model) {
        model.addAttribute("categories", categoryService.findAllCategory());
        return "admin/all-categories";
    }


    @GetMapping("/admin/create-category")
    public String create(Model model){
        model.addAttribute("category", new Category());
        return "admin/category-form";
    }


    @PostMapping("/admin/create-category")
    public String save(@Valid Category category,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "admin/category-form";
        } else {
            categoryService.saveCategory(category);
        }
        return "redirect:/admin/all-categories";
    }




}





















