package com.kevin.crudspring.dto.mapper;

import com.kevin.crudspring.dto.CourseDTO;
import com.kevin.crudspring.dto.LessonDTO;
import com.kevin.crudspring.enums.Category;
import com.kevin.crudspring.model.Course;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    public CourseDTO toDto(Course course){
        if(course == null){
            return null;
        }
        List<LessonDTO> lessons = course.getLessons()
                .stream()
                .map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl()))
                .collect(Collectors.toList());
        return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue(), lessons);
    }

    public Course toEntity(CourseDTO courseDTO){
        if(courseDTO == null){
            return null;
        }

        Course course = new Course();
        if(courseDTO.id() != null){
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(this.convertCategoryValue(courseDTO.category()));
        return course;
    }

    public Category convertCategoryValue(String value){
        if(value == null){
            return null;
        }
        return switch (value){
            case "Front-end" -> Category.FRONTEND;
            case "Back-end" -> Category.BACKEND;
            default -> throw new IllegalArgumentException("Categoria inválida: "+value);
        };
    }
}
