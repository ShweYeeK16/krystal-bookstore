package com.example.bookstoreapp.dao;

import com.example.bookstoreapp.entities.UserBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBooksDao extends JpaRepository<UserBooks, Integer> {

    @Query("select u from UserBooks u where u.users.email =:email")
    UserBooks findUserBooksByUserId(@Param("email") String email);
}
