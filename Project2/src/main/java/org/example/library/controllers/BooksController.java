package org.example.library.controllers;

import org.example.library.model.Book;
import org.example.library.model.Person;
import org.example.library.services.BooksService;
import org.example.library.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(@RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "books_per_page", required = false) Integer booksPerPage,
                        @RequestParam(value = "sort_by_year", required = false) boolean sortByYear,
                        Model model) {

        if (page == null || booksPerPage == null) {
            model.addAttribute("books", booksService.findAll());
        } else {
            model.addAttribute("books", booksService.findWithPagination(page, booksPerPage, sortByYear));
        }

        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, @ModelAttribute("person") Person person, Model model) {
        Book book = booksService.findById(id);
        model.addAttribute("book", book);
        model.addAttribute("people", peopleService.findAll());
        model.addAttribute("bookHandler", book.getPerson());
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
        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        booksService.update(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/add")
    public String addToPerson(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        booksService.addToPerson(id, person);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/return")
    public String returnBook(@PathVariable("id") int id) {
        booksService.returnBook(id);
        return "redirect:/books/" + id;
    }

    @GetMapping("/search")
    public String search() {
        return "books/search";
    }

    @PostMapping("/search")
    public String search(@RequestParam String query, Model model) {
        model.addAttribute("books", booksService.findBooksByTitleStartingWith(query));
        return "books/search";
    }


}
