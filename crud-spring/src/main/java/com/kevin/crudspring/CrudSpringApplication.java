package com.kevin.crudspring;

import com.kevin.crudspring.enums.Category;
import com.kevin.crudspring.model.Course;
import com.kevin.crudspring.model.Lesson;
import com.kevin.crudspring.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner databaseSeeding(CourseRepository courseRepository){
		return args -> {
			courseRepository.deleteAll();
			Course c = new Course();
			c.setName("Angular + Spring");
			c.setCategory(Category.FRONTEND);

			Lesson lesson = new Lesson();
			lesson.setName("Introdução");
			lesson.setYoutubeUrl("test");
			lesson.setCourse(c);
			c.getLessons().add(lesson);

			courseRepository.save(c);
		};
	}

}
