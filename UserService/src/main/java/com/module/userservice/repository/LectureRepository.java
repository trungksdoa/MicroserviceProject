package com.module.userservice.repository;

import com.module.userservice.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Stream;


@Repository
@Transactional
public interface LectureRepository extends JpaRepository<Lecture, String> {

    @Query("select l from Lecture l where l.lectureId = ?1")
    Optional<Lecture> fetchByLectureId(String lectureId);
    Lecture findByLectureId(String lectureId);
}
