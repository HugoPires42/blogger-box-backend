package com.dauphine.blogger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "blogger box backend",
				description = "Tout de suite ? Ah l'API a commencé ?",
				contact = @Contact(name = "Hugo",email = "hugosoulierpires@gmail.com"),
				version = "1.0.0"


		)
)
public class BloggerBoxBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggerBoxBackendApplication.class, args);
	}

}
