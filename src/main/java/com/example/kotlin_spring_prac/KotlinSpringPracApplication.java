package com.example.kotlin_spring_prac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class KotlinSpringPracApplication {

	public static void main(String[] args) {
		SpringApplication.run(KotlinSpringPracApplication.class, args);
	}

}
