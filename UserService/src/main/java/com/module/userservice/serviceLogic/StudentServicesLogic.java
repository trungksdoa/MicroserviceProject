package com.module.userservice.serviceLogic;

import com.module.userservice.dto.StudentCourseRegistrationDTO;
import com.module.userservice.exception.VerifyFailedException;
import com.module.userservice.model.BaseResponse;
import com.module.userservice.model.Student;
import com.module.userservice.restTemplate.ApiVersion;
import com.module.userservice.restTemplate.RestCall;
import com.module.userservice.restTemplate.UrlServiceInerface;
import com.module.userservice.serviceLogic.services.StudentServices;
import com.module.userservice.services.BaseMethodServices;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static com.module.userservice.enums.ErrorMessage.STUDENT_VERIFY_FAILED;
import static com.module.userservice.enums.StudentStatus.ACTIVE;
import static com.module.userservice.enums.StudentStatus.NON_ACTIVE;


@Service
@RequiredArgsConstructor
public class StudentServicesLogic implements StudentServices {

    private final BaseMethodServices<Student, String> baseService;
    private final UrlServiceInerface serviceInterface;
    private final RestTemplate restTemplate;

    @Value("${endpoints.courseService}")
    private String courseEndpoint;

    @Override
    public BaseResponse registerStudentToCourse(StudentCourseRegistrationDTO studentCourseRegistrationDTO,String url) {
        if (verifyStudent(studentCourseRegistrationDTO.getStudentId())) {
            throw new VerifyFailedException(STUDENT_VERIFY_FAILED.getMessage());
        }

        RestCall restCall = new RestCall(serviceInterface, restTemplate);

        //Check user
        return restCall.call(HttpMethod.POST, courseEndpoint, url, ApiVersion.interApi, studentCourseRegistrationDTO);
    }

    @Override
    public boolean verifyStudent(String studentId) {
        Optional<Student> studentOptional = baseService.get(studentId);
        if (studentOptional.isEmpty()) {
            return false;
        }
        String studentStatus = studentOptional.get().getStatus();
        return ACTIVE.getStatus().equals(studentStatus) || !NON_ACTIVE.getStatus().equals(studentStatus);
    }

}
