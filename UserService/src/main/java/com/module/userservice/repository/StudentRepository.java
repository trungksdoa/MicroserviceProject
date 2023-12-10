package com.module.userservice.repository;

import com.module.userservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, String> {
    Student findByStudentId(String studentId);

}
