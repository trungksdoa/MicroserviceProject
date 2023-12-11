package com.module.course.servicesLogic;

import com.module.course.common.Utils;
import com.module.course.dto.BaseResponse;
import com.module.course.dto.CourseDTO;
import com.module.course.exception.DataAlreadyExistsException;
import com.module.course.exception.DataNotFoundException;
import com.module.course.exception.EmptyListException;
import com.module.course.model.Course;
import com.module.course.services.CourseExtendService;
import com.module.course.servicesLogic.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

import static com.module.course.enums.ErrorMessage.*;
import static com.module.course.enums.SuccessMessage.*;

@Service("CourseServiceLogic")
@RequiredArgsConstructor
public class CourseServiceLogic implements CourseService {
    private final CourseExtendService courseExtendService;

    @Override
    public boolean verifyCourse(String courseId) {
        return false;
    }

    @Override
    public BaseResponse doSave(CourseDTO request) {
        Course course = Course.builder()
                .courseName(request.getCourseName())
                .courseType(request.getCourseType())
                .description(request.getDescription())
                .timeToComplete(request.getTimeToComplete())
                .status(request.getStatus())
                .catagoryId(request.getCategoryId())
                .lastModifiedBy(request.getLastModifiedBy())
                .courseId(Utils.getInstance().generateId(request.getCourseName()))
                .build();
        courseExtendService.get(course.getCourseId()).ifPresent(courseMaterial -> {
            throw new DataAlreadyExistsException(COURSE_SAVED_FAILED.getMessage());
        });
        return BaseResponse.builder().message(COURSE_SAVED_SUCCESS.getMessage()).data(courseExtendService.save(course)).build();
    }

    @Override
    public BaseResponse doUpdate(CourseDTO request) {
        Optional<Course> oldValue = courseExtendService.get(request.getCourseId());
        if (oldValue.isEmpty()) throw new DataNotFoundException(COURSE_UPDATED_FAILED.getMessage());
        return BaseResponse.builder().message(COURSE_UPDATED_SUCCESS.getMessage()).data(courseExtendService.save(getCourseUpdate(oldValue.get(), request))).build();
    }

    @Override
    public BaseResponse fetchAllCourse() {
        List<Course> courses = courseExtendService.get();
        if (courses.isEmpty()) throw new EmptyListException(COURSE_LIST_EMPTY.getMessage());

        return BaseResponse.builder().data(courses).message(COURSE_LIST_FOUND.getMessage()).build();
    }

    @Override
    public BaseResponse fetchCourseByName(String name) {
        return null;
    }

    @Override
    public BaseResponse fetchCourseById(String courseId) {
        if (courseExtendService.get(courseId).isEmpty()) throw new DataNotFoundException(COURSE_NOT_FOUND.getMessage());
        return BaseResponse.builder().data(courseExtendService.get(courseId).get()).message(COURSE_FOUND.getMessage()).build();
    }

    @Override
    public BaseResponse deleteCourse(CourseDTO request) {
        if (courseExtendService.get(request.getCourseId()).isEmpty())
            throw new DataNotFoundException(COURSE_DELETED_FAILED.getMessage());


        courseExtendService.delete(courseExtendService.get(request.getCourseId()).get());
        return BaseResponse.builder().message(COURSE_DELETED_SUCCESS.getMessage()).data(null).build();
    }

    public Course getCourseUpdate(Course oldValue, CourseDTO newValue) {
        return Course.builder().courseId(oldValue.getCourseId()).courseType(oldValue.getCourseType()).timeToComplete(oldValue.getTimeToComplete())
                //Wrap all the update value in Utils.getInstance().getUpdateValue
                .courseName(Utils.getInstance().getUpdateValue(newValue.getCourseName(), oldValue.getCourseName())).description(Utils.getInstance().getUpdateValue(newValue.getDescription(), oldValue.getDescription()))
                //Note later: change to lastModifiedBy automatically detect user and get the user
                .lastModifiedBy(Utils.getInstance().getUpdateValue(newValue.getLastModifiedBy(), oldValue.getLastModifiedBy()))
                //End
                .status(Utils.getInstance().getUpdateValue(newValue.getStatus(), oldValue.getStatus())).catagoryId(Utils.getInstance().getUpdateValue(newValue.getCategoryId(), oldValue.getCatagoryId())).build();
    }


}
