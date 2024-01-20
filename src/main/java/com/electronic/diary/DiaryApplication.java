package com.electronic.diary;

import com.electronic.diary.controllers.MainController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
