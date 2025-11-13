package com.mes_jyproject.master;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "testUserAuditing")
@OpenAPIDefinition(
		info = @Info(
				title = "Master Application",
				description = "MES Application with MSA Study example",
				version = "V2",
				contact = @Contact(
						name = "Lee JAe Young",
						email = "wodud4019@gmail.com",
						url = "MES"
				),
				license = @License(
						name = "apache 2.0",
						url = "http://mes.study.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "",
				url = "http://mes.study.com"
		)
)
public class MasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasterApplication.class, args);
	}

}
