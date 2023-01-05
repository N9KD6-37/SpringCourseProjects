package org.example.library.model;


import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class Book {
    private int id;
    @NotBlank(message = "Title should not be blank")
    @Size(min = 2, max = 50, message = "Title should be between 2 and 50")
    private String title;
    @Size(min = 2, max = 30, message = "Author should be between 2 and 30")
    @Pattern(regexp = "[A-ZА-Я]\\D+ [A-ZА-Я]\\D+( [A-ZА-Я]\\D)?", message = "Author should be fully identified")
    private String author;
    @Range(min = 1900, max = 2023, message = "Year of publication should be between 1900 and 2023")
    private int yearOfPublication;

    private int personId;

    public Book() {
    }

    public Book(int id, String title, String author, int yearOfPublication, int personId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.personId = personId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
