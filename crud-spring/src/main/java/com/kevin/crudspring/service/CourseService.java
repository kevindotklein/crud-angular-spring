package com.kevin.crudspring.service;

import com.kevin.crudspring.dto.CourseDTO;
import com.kevin.crudspring.dto.mapper.CourseMapper;
import com.kevin.crudspring.exception.RecordNotFoundException;
import com.kevin.crudspring.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public List<CourseDTO> findAll(){
        return this.courseRepository.findAll()
                .stream().map(courseMapper::toDto)
                .collect(Collectors.toList());
    }

    public CourseDTO findById(@NotNull @Positive Long id){
        return this.courseRepository.findById(id)
                .map(courseMapper::toDto)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO save(@Valid @NotNull CourseDTO course){
        return courseMapper.toDto(this.courseRepository.save(courseMapper.toEntity(course)));
    }

    public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO course){
        return this.courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.name());
                    recordFound.setCategory(course.category());
                    return this.courseRepository.save(recordFound);
                })
                .map(courseMapper::toDto)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        this.courseRepository.delete(this.courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }

}
