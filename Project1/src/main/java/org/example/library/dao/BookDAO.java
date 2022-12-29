package org.example.library.dao;

import org.example.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        BeanPropertyRowMapper<Book> rowMapper = new BeanPropertyRowMapper<>(Book.class);
        rowMapper.setPrimitivesDefaultedForNullValue(true);
        return jdbcTemplate.query("SELECT * FROM Book", rowMapper);
    }

    public Book show(int id) {
        BeanPropertyRowMapper<Book> rowMapper = new BeanPropertyRowMapper<>(Book.class);
        rowMapper.setPrimitivesDefaultedForNullValue(true);
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?",
                new Object[]{id},
                rowMapper).stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, year_of_publication) VALUES (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYearOfPublication());
    }

    public void update(Book book) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year_of_publication=? WHERE id=?",
                book.getTitle(), book.getAuthor(), book.getYearOfPublication(), book.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }
}
