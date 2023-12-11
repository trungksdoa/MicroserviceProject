package com.module.course.services;

import com.module.course.dto.BaseCourseDTO;
import com.module.course.enums.CourseEnum;
import com.module.course.model.Course;
import com.module.course.repositorys.CourseMaterialRepository;
import com.module.course.repositorys.CourseRepository;
import com.module.course.exception.DataAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service("courseService")
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseExtendService {

    private final CourseRepository courseRepository;

    @Override
    public List<Course> get() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> get(String id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course save(Course course) {
        return courseRepository.saveAndFlush(course);
    }

    @Override
    public void delete(Course course) {
        courseRepository.delete(course);
    }

    @Override
    public Optional<Course> getByCourseName(String name) {
        return courseRepository.findByCourseName(name);
    }
}