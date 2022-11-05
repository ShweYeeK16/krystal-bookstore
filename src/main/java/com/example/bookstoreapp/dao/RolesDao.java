package com.example.bookstoreapp.dao;

import com.example.bookstoreapp.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesDao extends JpaRepository<Roles, Integer> {
}
