package com.module.course.services;

import com.module.course.model.Course;

import java.util.Optional;

public interface CourseExtendService extends BaseMethodServices<Course, String> {
   public Optional<Course> getByCourseName(String name);
}
