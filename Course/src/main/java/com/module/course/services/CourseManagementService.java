package com.module.course.services;

import com.module.course.dto.BaseCourseDTO;
import com.module.course.enums.CourseEnum;
import com.module.course.model.Course;

import java.util.List;


public interface CourseManagementService {

    List<BaseCourseDTO> fetchAllCourses();

    Course fetchCourseById(Object id);

    Course fetchCourseByName(String name);

    Course addNewCourse(Course course);

    void removeCourseById(Object id);

    Course modifyCourseById(Object courseId, BaseCourseDTO baseCourseDTO);

    public boolean verifyCourse(String courseId);

    public CourseEnum determineCourseTypeById(String courseId);

}