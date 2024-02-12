package com.bogdan.library.service;

import com.bogdan.library.dto.BookDTO;
import com.bogdan.library.dto.PersonDTO;

import java.util.List;

public interface BookService {
    BookDTO getById(int id);
    List<BookDTO> getAll();
    BookDTO save(BookDTO dto);
    BookDTO update(int id,BookDTO dto);
    boolean delete(int id);
}
