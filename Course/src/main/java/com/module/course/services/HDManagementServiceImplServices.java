package com.module.course.services;

import com.module.course.model.StudentCourseRegistration;
import com.module.course.repositorys.CourseManagementRepository;
import com.module.course.exception.DataConflicException;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Component("studentService")
@NoArgsConstructor
public class HDManagementServiceImplServices implements CourseHDManagementServices {

    private CourseManagementRepository courseActionRepo;


    @Autowired
    public HDManagementServiceImplServices(CourseManagementRepository coursesRepo) {
        this.courseActionRepo = coursesRepo;
    }


    @Override
    @SneakyThrows
    public StudentCourseRegistration registerStudent(StudentCourseRegistration studentCourseRegistration) {
        if (isEnrolled(studentCourseRegistration.getCourseId(), studentCourseRegistration.getStudentId())) {
            throw new DataConflicException("Student already enrolled in course");
        }

        StudentCourseRegistration student = StudentCourseRegistration.builder()
                .studentId(studentCourseRegistration.getStudentId())
                .courseId(studentCourseRegistration.getCourseId())
                .studentName(studentCourseRegistration.getStudentName())
                .courseName(studentCourseRegistration.getCourseName())
                .build();

        return courseActionRepo.saveAndFlush(student);
    }


    @Override
    @SneakyThrows
    public void unrollStudent(String courseId, String studentId) {
        if (!isEnrolled(courseId, studentId)) {
            throw new RuntimeException("Student not enrolled in course");
        }

        courseActionRepo.deleteByStudentIdAndCourseId(studentId, courseId);

    }

    @Override
    @SneakyThrows
    public List<StudentCourseRegistration> fetchAllStudentByCourseId(String courseId) {
        return Optional.of(courseActionRepo.findByCourseId(courseId)).orElseThrow(() -> new NoSuchElementException("No student found with the provided ID"));
    }


    @Override
    public boolean isEnrolled(String courseId, String studentId) {
        return courseActionRepo.existsByStudentIdAndCourseId(studentId, courseId);
    }


}