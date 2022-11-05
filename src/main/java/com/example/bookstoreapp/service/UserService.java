package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dao.BookDao;
import com.example.bookstoreapp.dao.RolesDao;
import com.example.bookstoreapp.dao.UserBooksDao;
import com.example.bookstoreapp.dao.UsersDao;
import com.example.bookstoreapp.entities.*;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private UserBooksDao userBooksDao;

    @Autowired
    private RolesDao rolesDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void checkOut(Users users, List<BooksDto> booksDtos) {
        UserBooks userBooks = new UserBooks();
        Roles roles = new Roles();

        roles.setName("ROLE_USER");
        users.addRole(rolesDao.save(roles));
        users.setPassword(passwordEncoder.encode(users.getPassword()));

        userBooks.setUsers(usersDao.save(users));
        List<Books> booksList = bookDtoToEntity(booksDtos);

        for(Books books : booksList) {
            userBooks.addBooks(books);
        }

        userBooksDao.save(userBooks);
    }

    private List<Books> bookDtoToEntity(List<BooksDto> booksDtos) {

        List<Books> books = new ArrayList<>();

        for(BooksDto bookDto : booksDtos) {
            books.add(bookDao.getById(bookDto.getId()));
        }

        return books;
    }

    public List<Books> getBooksByUserId(String email) {
        return userBooksDao.findUserBooksByUserId(email)
                .getBooksList();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> users = usersDao.findByEmail(email);
        if(!users.isPresent()) {
            throw new UsernameNotFoundException(email + " NOT FOUND");
        }

        return users.get();
    }
}
