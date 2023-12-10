package com.module.course.services;

import com.module.course.model.StudentCourseRegistration;

import java.util.List;

public interface CourseHDManagementServices {

    public StudentCourseRegistration registerStudent(StudentCourseRegistration studentCourseRegistration);

    public boolean isEnrolled(String courseId, String studentId);

    public void unrollStudent(String courseId, String studentId);

    public List<StudentCourseRegistration> fetchAllStudentByCourseId(String courseId);
}
