package com.bogdan.library.service.impl;

import com.bogdan.library.dao.ManagerDao;
import com.bogdan.library.dto.BookDTO;
import com.bogdan.library.dto.PersonDTO;
import com.bogdan.library.mapper.BookMapper;
import com.bogdan.library.mapper.PersonMapper;
import com.bogdan.library.model.Book;
import com.bogdan.library.model.Person;
import com.bogdan.library.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerServiceImpl implements ManagerService {

    private final ManagerDao managerDao;
    private BookMapper bookMapper;
    private PersonMapper personMapper;

    public ManagerServiceImpl(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }
    @Autowired
    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Autowired
    public void setPersonMapper(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    @Override
    public List<BookDTO> getBooksPerson(int id) {
        List<Book> booksPerson = managerDao.getBooksPerson(id);
        return booksPerson.stream()
                .map(bookMapper::map)
                .toList();
    }

    @Override
    public PersonDTO getBookOwner(int id) {
        Optional<Person> bookOwner = managerDao.getBookOwner(id);
        return bookOwner.map(person -> personMapper.map(person)).orElse(null);
    }

    @Override
    public void assign(int personId,int bookId) {
        managerDao.assign(personId,bookId);
    }

    @Override
    public void release(int bookId) {
        managerDao.release(bookId);
    }
}
