package com.module.course.services;

import com.module.course.model.StudentCourseRegistration;

import java.util.Optional;

public interface CourseRegistrationExtendService extends BaseMethodServices<StudentCourseRegistration, Integer> {
    public void deleteByStudentId(String studentId);
    public Optional<StudentCourseRegistration> getByStudentId(String studentId);
}
