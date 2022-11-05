package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dao.RolesDao;
import com.example.bookstoreapp.dao.UsersDao;
import com.example.bookstoreapp.entities.Roles;
import com.example.bookstoreapp.entities.Users;
import com.example.bookstoreapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.management.relation.Role;

@Controller
public class AccountController {

    @Autowired
    public UsersDao usersDao;

    @Autowired
    private RolesDao rolesDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    private String login() {
        return "account/login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError",true);
        return "account/login";
    }

    @GetMapping("/make/admin")
    @ResponseBody
    @Transactional
    public String makeAdmin() {
        Users users = new Users();

        users.setPassword(passwordEncoder.encode("16303"));
        users.setEmail("byekrystal@gmail.com");
        users.setFirstName("Krystal");
        users.setLastName("Chloe");
        users.setAddress("Hogwarts");
        users.setPhoneNumber("222-22-20");

        Roles roles = new Roles();
        roles.setName("ROLE_ADMIN");

        users.addRole(rolesDao.save(roles));
        usersDao.save(users);

        return "SUCCESSFUL ADMIN";
    }
}


















