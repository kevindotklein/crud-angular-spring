package com.kevin.crudspring.service;

import com.kevin.crudspring.model.Course;
import com.kevin.crudspring.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> findAll(){
        return this.courseRepository.findAll();
    }

    public Optional<Course> findById(@NotNull @Positive Long id){
        return this.courseRepository.findById(id);
    }

    public Course save(@Valid Course course){
        return this.courseRepository.save(course);
    }

    public Optional<Course> update(@NotNull @Positive Long id, @Valid Course course){
        return this.courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.getName());
                    recordFound.setCategory(course.getCategory());
                    return this.courseRepository.save(recordFound);
                });
    }

    public boolean delete(@NotNull @Positive Long id){
        return this.courseRepository.findById(id)
                .map(recordFound -> {
                    this.courseRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }

}
