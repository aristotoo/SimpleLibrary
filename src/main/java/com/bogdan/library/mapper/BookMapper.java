package com.bogdan.library.mapper;

import com.bogdan.library.dto.BookDTO;
import com.bogdan.library.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public Book create(BookDTO bookDTO) {
        Book book = new Book();
        book.setAuthor(bookDTO.getAuthor());
        book.setTitle(bookDTO.getTitle());
        book.setYear(bookDTO.getYear());
        return book;
    }
    public BookDTO map(Book entity) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(entity.getBookId());
        bookDTO.setTitle(entity.getTitle());
        bookDTO.setAuthor(entity.getAuthor());
        bookDTO.setYear(entity.getYear());
        return bookDTO;
    }
}
