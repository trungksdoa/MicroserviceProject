package com.module.course.repositorys;

import com.module.course.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository("courseRespository")
public interface CourseRepository extends JpaRepository<Course, String> {
    @Query("select (count(c) > 0) from Course c where c.courseId = ?1")
    boolean verifyCourse(@NonNull String courseId);
    boolean existsByCourseName(String courseName);

    @Modifying
    @Query("delete from Course c where c.courseId = ?1")
    void deleteCourseById(String courseId);

    @Query(value = "SELECT * FROM course WHERE course_name = ?1 and status = 'ACTIVE'", nativeQuery = true)
    public Optional<Course> fetchSpecificCourseInformationByName(String courseName);

    @Query(value = "SELECT c.courseType FROM Course c WHERE c.courseId = ?1 and c.status = 'ACTIVE'")
    public String determineCourseTypeByCourseId(String courseId);


    @Query(value = "SELECT c.courseType FROM Course c WHERE c.courseId = ?1 and c.status = 'ACTIVE'")
    public boolean checkCourseAttempt(String courseId);

    Optional<Course> findByCourseName(String courseName);
}
