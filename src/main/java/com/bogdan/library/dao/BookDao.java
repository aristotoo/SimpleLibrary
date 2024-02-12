package com.bogdan.library.dao;

import com.bogdan.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@PropertySource("classpath:query.properties")
public class BookDao implements Dao<Book>{

    private final JdbcTemplate jdbcTemplate;
    private Environment environment;

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate){

        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
    @Override
    public Optional<Book> getById(int id) {
        return jdbcTemplate.query(Objects.requireNonNull(environment.getProperty("getBookById")),
                        new BeanPropertyRowMapper<>(Book.class),
                        getArguments(id))
                .stream().findAny();
    }
    private Object[] getArguments(Object...args){
        return args;
    }

    @Override
    public List<Book> getAll() {
        return jdbcTemplate.query(Objects.requireNonNull(environment.getProperty("getAllBook")),
                new BeanPropertyRowMapper<>(Book.class));
    }
    @Override
    public void save(Book book) {
        jdbcTemplate.update(Objects.requireNonNull(environment.getProperty("saveBook")),
                book.getTitle(),
                book.getAuthor(),
                book.getYear()
        );
    }
    @Override
    public void update(int id,Book book) {
        jdbcTemplate.update(Objects.requireNonNull(environment.getProperty("updateBook")),
                book.getTitle(),
                book.getAuthor(),
                book.getYear(),
                id
        );
    }
    @Override
    public int delete(int id) {
       return jdbcTemplate.update(Objects.requireNonNull(environment.getProperty("deleteBook")),id);
    }
}
