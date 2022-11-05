package com.example.bookstoreapp.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category extends IdClass {


    //@NotEmpty(message = "{course.name.valid.empty}")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Books> booksList = new ArrayList<>();

    public Books addCourse(Books books) {
        books.setCategory(this);
        booksList.add(books);
        return books;
    }

    public void remove() {
        booksList.remove(this);
        //this.getCourseList().remove(this);
    }

}
