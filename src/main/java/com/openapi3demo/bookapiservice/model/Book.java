package com.openapi3demo.bookapiservice.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@Document(value = "books")
public class Book {

    @Id
    private String id;

    private String name;

    private String description;

    private String author;

    private String isbn;

    private Date createdAt;

    private Date lastUpdatedAt;

}
