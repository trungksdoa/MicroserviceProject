package com.module.userservice.services;

import com.module.userservice.model.Student;
import com.module.userservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;


@Component
public class StudentServicesImpl implements StudentServices {

    private final StudentRepository studentRepository;


    @Autowired
    public StudentServicesImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public String getCurrentCourse(String studentId) {
        return getStudent(studentId).getCurrentCourse();
    }

    @Override
    public String getStudentStatus(String studentId) {
        return getStudent(studentId).getStatus();
    }

    @Override
    public String verifyStudent(String studentId) {
        return getStudent(studentId) != null ? "valid" : "invalid";
    }

    @Override
    public int getStudentRank(String studentId) {
        return getStudent(studentId).getStudentRank();
    }

    @Override
    public Long getAccumulatedPoints(String studentId) {
        return getStudent(studentId).getAccumulatedPoints();
    }

    @Override
    public int getCourseCompleted(String studentId) {
        return getStudent(studentId).getCourseCompleted();
    }

    @Override
    public int getTotalTeacherReview(String studentId) {
        return getStudent(studentId).getTotalTeacherReview();
    }

    public Student getStudent(String studentId) {
        Student student = studentRepository.findByStudentId(studentId);
        if (student == null) {
            throw new NoSuchElementException("Student not found"); // This custom exception with response to student page 404, But how ?
        }
        return student;
    }
}
