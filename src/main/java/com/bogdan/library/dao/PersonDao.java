package com.bogdan.library.dao;

import com.bogdan.library.model.Person;
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
public class PersonDao implements Dao<Person> {

    private final JdbcTemplate jdbcTemplate;
    private Environment environment;

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate){

        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
    @Override
    public Optional<Person> getById(int id) {
        return jdbcTemplate.query(Objects.requireNonNull(environment.getProperty("getById")), new BeanPropertyRowMapper<>(Person.class),getArguments(id))
                .stream().findAny();
    }
    private Object[] getArguments(Object...args){
        return args;
    }

    @Override
    public List<Person> getAll() {
        return jdbcTemplate.query(Objects.requireNonNull(environment.getProperty("getAllPeople")),
                new BeanPropertyRowMapper<>(Person.class));
    }

    @Override
    public void save(Person person) {
        jdbcTemplate.update(Objects.requireNonNull(environment.getProperty("save")),
                person.getFullName(),
                person.getYearOfBirth()
        );
    }
    @Override
    public void update(int id,Person person) {
        jdbcTemplate.update(Objects.requireNonNull(environment.getProperty("update")),
                person.getFullName(),
                person.getYearOfBirth(),
                id
        );
    }
    @Override
    public int delete(int id) {
        return jdbcTemplate.update(Objects.requireNonNull(environment.getProperty("delete")),id);
    }

    public Optional<Person> getByFullName(String fullName) {
        return jdbcTemplate.query(Objects.requireNonNull(environment.getProperty("getByFullName")),
                new BeanPropertyRowMapper<>(Person.class),getArguments(fullName)).stream().findFirst();
    }
}
