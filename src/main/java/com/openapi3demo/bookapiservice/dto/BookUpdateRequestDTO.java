package com.openapi3demo.bookapiservice.dto;

import lombok.Data;

@Data
public class BookUpdateRequestDTO {

    private String id;

    private String name;

    private String description;

    private String isbn;

    private String authorName;

}
