package com.module.course.servicesLogic;

import com.module.course.dto.BaseResponse;
import com.module.course.dto.StudentCourseRegistrationDTO;
import com.module.course.exception.DataAlreadyExistsException;
import com.module.course.model.StudentCourseRegistration;
import com.module.course.services.CourseRegistrationExtendService;
import com.module.course.servicesLogic.services.CourseRegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import static com.module.course.enums.RegistrationUpdateMessages.EXISTING_REGISTRATION;
import static com.module.course.enums.RegistrationUpdateMessages.NON_EXISTING_REGISTRATION;


@Service("RegistrationServiceLogic")
@RequiredArgsConstructor
public class RegistrationServiceLogic implements CourseRegistrationService {

    private final CourseRegistrationExtendService service;

    @Override
    @SneakyThrows
    public BaseResponse register(StudentCourseRegistrationDTO studentCourseRegistration) {
        if (service.getByStudentId(studentCourseRegistration.getStudentId()).isPresent()) {
            throw new DataAlreadyExistsException(EXISTING_REGISTRATION.format(studentCourseRegistration.getStudentId(), studentCourseRegistration.getCourseName()));
        }

        StudentCourseRegistration student = StudentCourseRegistration.builder()
                .studentId(studentCourseRegistration.getStudentId())
                .courseId(studentCourseRegistration.getCourseId())
                .studentName(studentCourseRegistration.getStudentName())
                .courseName(studentCourseRegistration.getCourseName())
                .build();


        return BaseResponse.<StudentCourseRegistration>builder()
                .message(NON_EXISTING_REGISTRATION.format(studentCourseRegistration.getStudentId(), studentCourseRegistration.getCourseName()))
                .data(service.save(student))
                .build();
    }
}
