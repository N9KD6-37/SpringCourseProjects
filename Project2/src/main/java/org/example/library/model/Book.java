package org.example.library.model;


import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    @NotBlank(message = "Title should not be blank")
    @Size(min = 2, max = 50, message = "Title should be between 2 and 50")
    private String title;
    @Column(name = "author")
    @Size(min = 2, max = 30, message = "Author should be between 2 and 30")
    @Pattern(regexp = "[A-ZА-Я]\\D+ [A-ZА-Я]\\D+( [A-ZА-Я]\\D)?", message = "Author should be fully identified")
    private String author;
    @Column(name = "year_of_publication")
    @Range(min = 1900, max = 2023, message = "Year of publication should be between 1900 and 2023")
    private int yearOfPublication;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Column(name = "taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;

    @Transient
    private boolean isOverdue;

    public Book() {
    }

    public Book(String title, String author, int yearOfPublication) {
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }

    public boolean isOverdue() {
        if (takenAt == null)
            return false;
        Date now = new Date();
        long diffInMillies = Math.abs(now.getTime() - takenAt.getTime());
        isOverdue = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) > 10;
        return isOverdue;
    }
}
