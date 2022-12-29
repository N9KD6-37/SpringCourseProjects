package org.example.library.model;


import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class Person {
    private int id;
    @Pattern(regexp = "[A-ZА-Я]\\D+ [A-ZА-Я]\\D+( [A-ZА-Я]\\D)?", message = "Name should be fully identified")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;
    @Range(min = 1900, max = 2017, message = "Year of birth should be between 1900 and 2017")
    private int yearOfBirth;

    public Person() {
    }

    public Person(int id, String name, int yearOfBirth) {
        this.id = id;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public String toString() {
        return getName() + ", " + getYearOfBirth();
    }
}
