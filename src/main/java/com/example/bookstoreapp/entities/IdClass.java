package com.example.bookstoreapp.entities;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
public class IdClass {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
}
