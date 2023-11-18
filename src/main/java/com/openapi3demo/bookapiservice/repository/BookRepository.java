package com.openapi3demo.bookapiservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.openapi3demo.bookapiservice.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findByName(String name);

    List<Book> findByAuthor(String authorName);

}
