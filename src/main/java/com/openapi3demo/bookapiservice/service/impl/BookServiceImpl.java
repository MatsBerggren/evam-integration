package com.openapi3demo.bookapiservice.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openapi3demo.bookapiservice.dto.BookRequestDTO;
import com.openapi3demo.bookapiservice.dto.BookUpdateRequestDTO;
import com.openapi3demo.bookapiservice.model.Book;
import com.openapi3demo.bookapiservice.repository.BookRepository;
import com.openapi3demo.bookapiservice.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book createNew(BookRequestDTO bookRequestDTO) {
        return bookRepository.save(
                Book.builder()
                        .name(bookRequestDTO.getName())
                        .description(bookRequestDTO.getDescription())
                        .author(bookRequestDTO.getAuthorName())
                        .isbn(bookRequestDTO.getIsbn())
                        .createdAt(new Date(System.currentTimeMillis()))
                        .lastUpdatedAt(new Date(System.currentTimeMillis()))
                        .build()
        );
    }

    @Override
    public Book updateBook(BookUpdateRequestDTO bookUpdateRequestDTO) {
        Optional<Book> existingBook = bookRepository.findById(bookUpdateRequestDTO.getId());
        if(existingBook.isEmpty())
            throw new RuntimeException(
                    String.format("No Book found for id: %s", bookUpdateRequestDTO.getId()));
        existingBook.get().setName(bookUpdateRequestDTO.getName());
        existingBook.get().setDescription(bookUpdateRequestDTO.getDescription());
        existingBook.get().setAuthor(bookUpdateRequestDTO.getAuthorName());
        existingBook.get().setIsbn(bookUpdateRequestDTO.getIsbn());
        return bookRepository.save(existingBook.get());
    }

    @Override
    public Boolean deleteById(String id) {
        bookRepository.deleteById(id);
        if(bookRepository.findById(id).isEmpty())
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    @Override
    public List<Book> getByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    @Override
    public List<Book> getByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public Book getById(String bookId) {
        return bookRepository.findById(bookId).orElseThrow(
                () -> new RuntimeException("No Book found for id: %s".formatted(bookId))
        );
    }
}
