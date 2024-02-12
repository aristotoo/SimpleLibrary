package com.bogdan.library.dao;

import com.bogdan.library.model.Book;
import com.bogdan.library.model.Person;

import java.util.List;
import java.util.Optional;

public interface ManagerDao {
    List<Book> getBooksPerson(int id);

    Optional<Person> getBookOwner(int id);

    void assign(int personId,int bookId);
    void release(int bookId);

}
