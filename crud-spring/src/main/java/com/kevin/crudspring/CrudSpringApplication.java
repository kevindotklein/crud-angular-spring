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

			Lesson l = new Lesson();
			l.setName("Introdução");
			l.setYoutubeUrl("test");
			l.setCourse(c);
			c.getLessons().add(l);

			Lesson l2 = new Lesson();
			l2.setName("Introdução2");
			l2.setYoutubeUrl("test2");
			l2.setCourse(c);
			c.getLessons().add(l2);

			courseRepository.save(c);
		};
	}

}
