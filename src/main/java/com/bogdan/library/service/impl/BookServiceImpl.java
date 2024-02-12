package com.bogdan.library.service.impl;

import com.bogdan.library.dao.BookDao;
import com.bogdan.library.dto.BookDTO;
import com.bogdan.library.mapper.BookMapper;
import com.bogdan.library.model.Book;
import com.bogdan.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private BookMapper bookMapper;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }
    @Autowired
    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public BookDTO getById(int id) {
        Optional<Book> byId = bookDao.getById(id);
        BookDTO result;
        if(byId.isPresent())
            result = bookMapper.map(byId.get());
        else {
            result = new BookDTO();
            result.setTitle("Not Found");
            result.setAuthor("None");
            result.setYear("-1111");
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> getAll() {
        return bookDao.getAll()
                .stream()
                .map(bookMapper::map)
                .toList();
    }

    @Override
    @Transactional
    public BookDTO save(BookDTO dto) {
        Book book = bookMapper.create(dto);
        bookDao.save(book);
        return dto;
    }

    @Override
    @Transactional
    public BookDTO update(int id, BookDTO dto) {
        Book book = bookMapper.create(dto);
        bookDao.update(id,book);
        return dto;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return bookDao.delete(id) > 0;
    }
}
