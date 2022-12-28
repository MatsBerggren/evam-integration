package com.openapi3demo.bookapiservice.controller;

import com.openapi3demo.bookapiservice.dto.BookRequestDTO;
import com.openapi3demo.bookapiservice.dto.BookUpdateRequestDTO;
import com.openapi3demo.bookapiservice.model.Book;
import com.openapi3demo.bookapiservice.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@Tag(name = "Book Operation APIs", description = "API collection for CRUD operations on Book Resource")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping
    public Book createNew(@RequestBody BookRequestDTO bookRequestDTO){
        return bookService.createNew(bookRequestDTO);
    }

    @GetMapping
    public Book getById(@RequestParam String bookId){
        return bookService.getById(bookId);
    }

    @GetMapping("getByName")
    public List<Book> getByName(@RequestParam String name){
        return bookService.getByName(name);
    }

    @GetMapping("getByAuthor")
    public List<Book> getByAuthor(@RequestParam String authorName){
        return bookService.getByAuthor(authorName);
    }

    @PutMapping
    public Book updateBook(@RequestBody BookUpdateRequestDTO bookUpdateRequestDTO){
        return bookService.updateBook(bookUpdateRequestDTO);
    }

    @DeleteMapping
    public Boolean deleteBook(@RequestParam String bookId){
        return bookService.deleteById(bookId);
    }

}
