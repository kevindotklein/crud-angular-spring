package com.kevin.crudspring.service;

import com.kevin.crudspring.exception.RecordNotFoundException;
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

    public Course findById(@NotNull @Positive Long id){
        return this.courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Course save(@Valid Course course){
        return this.courseRepository.save(course);
    }

    public Course update(@NotNull @Positive Long id, @Valid Course course){
        return this.courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.getName());
                    recordFound.setCategory(course.getCategory());
                    return this.courseRepository.save(recordFound);
                })
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        this.courseRepository.delete(this.courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }

}
