package com.openapi3demo.bookapiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(
				title = "Evam integration API",
				description = "This integration connect Evam to amPHI through amPHI's CSAM API.",
				version = "0.0.1",
				contact = @Contact(email = "mats.berggren@dedalus.com", name = "Mats Berggren")
		)
)
@SpringBootApplication
public class EvamIntegrationService {

	public static void main(String[] args) {
		SpringApplication.run(EvamIntegrationService.class, args);
	}

}
