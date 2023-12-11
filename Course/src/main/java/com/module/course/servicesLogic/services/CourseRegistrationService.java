package com.module.course.servicesLogic.services;

import com.module.course.dto.BaseResponse;
import com.module.course.dto.StudentCourseRegistrationDTO;

public interface CourseRegistrationService {

    public BaseResponse register(StudentCourseRegistrationDTO studentCourseRegistration);
}
