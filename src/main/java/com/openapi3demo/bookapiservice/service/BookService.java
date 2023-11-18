package com.openapi3demo.bookapiservice.service;

import java.util.List;

import com.openapi3demo.bookapiservice.dto.BookRequestDTO;
import com.openapi3demo.bookapiservice.dto.BookUpdateRequestDTO;
import com.openapi3demo.bookapiservice.model.Book;

public interface BookService {

    Book createNew(BookRequestDTO bookRequestDTO);

    Book updateBook(BookUpdateRequestDTO bookUpdateRequestDTO);

    Boolean deleteById(String id);

    List<Book> getByAuthor(String author);

    List<Book> getByName(String name);

    Book getById(String bookId);

}
