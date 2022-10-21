package com.lwin.emr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class EmrApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmrApplication.class, args);
	}

	@Bean
	public OpenAPI generateOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("LWIN EMR")
						.description("Assignment")
						.version("v1.0.0")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}

}
