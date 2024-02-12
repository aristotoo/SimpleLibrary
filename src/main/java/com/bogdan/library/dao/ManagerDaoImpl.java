package com.bogdan.library.dao;

import com.bogdan.library.model.Book;
import com.bogdan.library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@PropertySource("classpath:query.properties")
public class ManagerDaoImpl implements ManagerDao{

    private final JdbcTemplate jdbcTemplate;
    private Environment environment;

    @Autowired
    public ManagerDaoImpl(JdbcTemplate jdbcTemplate){

        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void assign(int personId,int bookId) {
        jdbcTemplate.update(Objects.requireNonNull(environment.getProperty("assignBook")),personId,bookId);
    }

    @Override
    public void release(int bookId) {
        jdbcTemplate.update(Objects.requireNonNull(environment.getProperty("releaseBook")),bookId);
    }

    @Override
    public Optional<Person> getBookOwner(int id) {
        try{
            return Optional.ofNullable(jdbcTemplate.queryForObject(Objects.requireNonNull(environment.getProperty("getBookOwner")),
                    new BeanPropertyRowMapper<>(Person.class),id));
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }
    @Override
    public List<Book> getBooksPerson(int id) {
        return jdbcTemplate.query(Objects.requireNonNull(environment.getProperty("getBooksPerson")),
                new ResultSetExtractor<List<Book>>() {
                    @Override
                    public List<Book> extractData(@NonNull ResultSet rs) throws SQLException, DataAccessException {
                        List<Book> books = new ArrayList<>();
                        while(rs.next()) {
                            Book book = new Book();
                            book.setTitle(rs.getString("title"));
                            book.setAuthor(rs.getString("author"));
                            book.setYear(rs.getString("year"));
                            books.add(book);
                        }
                        return books;
                    }
                }, id);
    }
}
