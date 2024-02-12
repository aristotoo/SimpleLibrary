package com.bogdan.library.service;

import com.bogdan.library.dto.BookDTO;
import com.bogdan.library.dto.PersonDTO;
import com.bogdan.library.model.Book;
import com.bogdan.library.model.Person;

import java.util.List;
import java.util.Optional;

public interface ManagerService {
    List<BookDTO> getBooksPerson(int id);

    PersonDTO getBookOwner(int id);

    void assign(int personId,int bookId);
    void release(int bookId);
}
