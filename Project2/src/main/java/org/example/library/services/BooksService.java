package org.example.library.services;

import org.example.library.model.Book;
import org.example.library.model.Person;
import org.example.library.repositories.BooksRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findById(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void addToPerson(int id, Person person) {
        Book book = booksRepository.findById(id).orElse(null);
        if (book == null)
            return;
        book.setPerson(person);
        book.setTakenAt(new Date());
        booksRepository.save(book);
    }

    @Transactional
    public void returnBook(int id) {
        Book book = booksRepository.findById(id).orElse(null);
        if (book == null)
            return;
        book.setPerson(null);
        book.setTakenAt(null);
        booksRepository.save(book);
    }
}