package com.module.course.services;

import com.module.course.dto.BaseCourseDTO;
import com.module.course.enums.CourseEnum;
import com.module.course.model.Course;
import com.module.course.repositorys.CourseMaterialRepository;
import com.module.course.repositorys.CourseRepository;
import com.module.course.exception.DataConflicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Component("normalCourseService")
public class CourseServiceImpl implements CourseManagementService {

    private final CourseRepository courseRepository;

    private final CourseMaterialRepository contentRepo;

    @Autowired
    public CourseServiceImpl(CourseRepository respository, CourseMaterialRepository contentRepo) {
        this.courseRepository = respository;
        this.contentRepo = contentRepo;
    }

    @Override
    public List<BaseCourseDTO> fetchAllCourses() {
        return Optional.of(courseRepository.findAll())
                .map(res -> res.stream()
                        .map(course -> course.toBaseCourseDTO(contentRepo.findByCourseId(course.getCourseId())))
                        .toList())
                .filter(course -> !course.isEmpty())
                .orElseThrow(() -> new NoSuchElementException("No material found, try again"));
    }

    @Override
    public Course fetchCourseById(Object courseId) {
        //Get string from courseId
        return courseRepository.findById(courseId.toString()).orElseThrow(() -> new NoSuchElementException("No course found"));
    }

    @Override
    public Course fetchCourseByName(String courseName) {
        return courseRepository.fetchSpecificCourseInformationByName(courseName).orElseThrow(() -> new NoSuchElementException("No course found"));
    }

    @Override
    public Course addNewCourse(Course course) {
        String generatedCourseId = generateCourseId(course.getCourseName());
        if (verifyCourse(generatedCourseId)) throw new DataConflicException("Course already exist");
        course.setCourseId(generatedCourseId);
        return courseRepository.saveAndFlush(course);
    }

    private String generateCourseId(String courseName) {
        return courseName + "-" + LocalDate.now().getYear();
    }

    @Override
    public void removeCourseById(Object courseId) {
        if (!courseRepository.verifyCourse(courseId.toString())) {
            throw new NoSuchElementException("No course found");
        }
        courseRepository.deleteCourseById(courseId.toString());
    }


    @Override
    public Course modifyCourseById(Object courseId, BaseCourseDTO baseCourseDTO) {
        Course courseOption = fetchCourseById(courseId.toString());

        return Course.builder()
                .courseName(getUpdatedValue(baseCourseDTO.getCourseName(), courseOption.getCourseName()))
                .description(getUpdatedValue(baseCourseDTO.getDescription(), courseOption.getDescription()))
                .lastModifiedBy(getUpdatedValue(baseCourseDTO.getLastModifiedBy(), courseOption.getLastModifiedBy()))
                .status(getUpdatedValue(baseCourseDTO.getStatus(), courseOption.getStatus()))
//                .totalAttempt(getUpdatedValue(baseCourseDTO.getTotalAttempt(), courseOption.getTotalAttempt()))
                .catagoryId(getUpdatedValue(baseCourseDTO.getCatagoryId(), courseOption.getCatagoryId()))
                .build();

    }

    @Override
    public boolean verifyCourse(String courseId) {
        return courseRepository.verifyCourse(courseId);
    }

    @Override
    public CourseEnum determineCourseTypeById(String courseId) {
        return courseRepository.determineCourseTypeByCourseId(courseId).equals("group") ? CourseEnum.group : CourseEnum.personal;
    }

    private <T> T getUpdatedValue(T newValue, T defaultValue) {
        return newValue != null ? newValue : defaultValue;
    }


}
