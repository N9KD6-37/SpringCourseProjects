package org.example.library.controllers;

import org.example.library.dao.BookDAO;
import org.example.library.dao.PersonDAO;
import org.example.library.model.Book;
import org.example.library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, @ModelAttribute("person") Person person, Model model) {
        Book book = bookDAO.show(id);
        model.addAttribute("book", book);
        model.addAttribute("people", personDAO.index());
        model.addAttribute("bookHandler", personDAO.show(book.getPersonId()));
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookDAO.update(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/add")
    public String addToPerson(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        bookDAO.addToPerson(id, person.getId());
        return "redirect:/books/" + id;
    }

    @PatchMapping("{id}/return")
    public String returnBook(@PathVariable("id") int id) {
        bookDAO.removeFromPerson(id);
        return "redirect:/books/" + id;
    }
}
