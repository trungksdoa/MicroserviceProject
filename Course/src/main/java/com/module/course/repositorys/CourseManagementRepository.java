package com.module.course.repositorys;

import com.module.course.model.StudentCourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CourseManagementRepository extends JpaRepository<StudentCourseRegistration, Integer> {
    @Query("select s from StudentCourseRegistration s where s.courseId = ?1")
    List<StudentCourseRegistration> findByCourseId(String courseId);

//    @Query(value = "DELETE FROM student_participant WHERE student_id = ?1 AND course_id = ?2", nativeQuery = true)
    void deleteByStudentIdAndCourseId(String studentId, String courseId);
    boolean existsByStudentIdAndCourseId(String studentId, String courseId);
    boolean existsByStudentId(String studentId);

    Optional<StudentCourseRegistration> findByStudentId(String studentId);

    void deleteByStudentId(String studentId);

}
