package com.module.course.servicesLogic.services;

import com.module.course.dto.BaseCourseDTO;
import com.module.course.dto.BaseResponse;
import com.module.course.dto.CourseDTO;
import com.module.course.enums.CourseEnum;
import com.module.course.model.Course;

import java.util.List;


public interface CourseService {

    public boolean verifyCourse(String courseId);
    public BaseResponse doSave(CourseDTO course);
    public BaseResponse doUpdate(CourseDTO course);
    public BaseResponse fetchAllCourse();
    public BaseResponse fetchCourseByName(String name);
    public BaseResponse fetchCourseById(String courseId);
    public BaseResponse deleteCourse(CourseDTO course);

}