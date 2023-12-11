package com.module.course.services;

import com.module.course.model.StudentCourseRegistration;
import com.module.course.repositorys.CourseManagementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service("studentService")
@RequiredArgsConstructor
public class RegistrationServiceImpl implements CourseRegistrationExtendService {

    private final CourseManagementRepository repo;


    @Override
    public List<StudentCourseRegistration> get() {
        return repo.findAll();
    }

    @Override
    public Optional<StudentCourseRegistration> get(Integer integer) {
        return repo.findById(integer);
    }

    @Override
    public StudentCourseRegistration save(StudentCourseRegistration studentCourseRegistration) {
        return repo.saveAndFlush(studentCourseRegistration);
    }

    @Override
    public void delete(StudentCourseRegistration studentCourseRegistration) {
        repo.delete(studentCourseRegistration);
    }


    @Override
    public void deleteByStudentId(String studentId) {
        repo.deleteByStudentId(studentId);
    }

    @Override
    public Optional<StudentCourseRegistration> getByStudentId(String studentId) {
        return repo.findByStudentId(studentId);
    }
}