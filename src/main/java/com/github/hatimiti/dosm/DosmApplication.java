package com.github.hatimiti.dosm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(DosmConfig.class)
public class DosmApplication {

	public static void main(String[] args) {
		SpringApplication.run(DosmApplication.class, args);
	}
}
