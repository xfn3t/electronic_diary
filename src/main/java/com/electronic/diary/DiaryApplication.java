package com.electronic.diary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//@Configuration
//@EnableJpaRepositories(basePackages = "com.electronic.diary.repository")
//@ComponentScan(base	Packages = {"com.electronic.diary.controllers", "com.electronic.diary.service", "com.electronic.diary.repository"})
@EntityScan(basePackages = "com.electronic.diary.DTO")

public class DiaryApplication {
	public static void main(String[] args) {
		SpringApplication.run(DiaryApplication.class, args);
	}
}
