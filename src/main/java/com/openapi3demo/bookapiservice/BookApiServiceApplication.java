package com.openapi3demo.bookapiservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Book service API collection",
				description = "Book service APIs for book operations",
				version = "0.0.1",
				contact = @Contact(email = "vishu@vishu.com")
		)
)
@SpringBootApplication
public class BookApiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookApiServiceApplication.class, args);
	}

}
