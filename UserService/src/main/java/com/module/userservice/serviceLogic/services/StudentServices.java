package com.module.userservice.serviceLogic.services;

import com.module.userservice.dto.StudentCourseRegistrationDTO;
import com.module.userservice.model.BaseResponse;
import com.module.userservice.model.StudentCourseRegistration;

public interface StudentServices {

    BaseResponse registerStudentToCourse(StudentCourseRegistrationDTO studentCourseRegistrationDTO,String url);

    // Phương thức trả về khóa học hiện tại của sinh viên
    boolean verifyStudent(String studentId);
}
