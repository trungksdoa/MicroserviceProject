package com.module.course.repositorys;

import com.module.course.model.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Integer> {

    List<CourseMaterial> findByCourseId(String courseId);
    @Override
    Optional<CourseMaterial> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);
}
