package com.example.arst5backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Arst5backendApplication {

	public static void main(String[] args) {
		SpringApplication.run(Arst5backendApplication.class, args);
	}

}
