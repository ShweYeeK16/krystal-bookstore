package com.example.bookstoreapp.dao;


import com.example.bookstoreapp.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Books,Integer> {
}
