package com.module.userservice.controller;


import com.module.userservice.dto.StudentCourseRegistrationDTO;
import com.module.userservice.model.BaseResponse;
import com.module.userservice.restTemplate.ApiVersion;
import com.module.userservice.restTemplate.RestCall;
import com.module.userservice.restTemplate.UrlServiceInerface;

import com.module.userservice.serviceLogic.services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/v1/api/student")
public class handleController {
    private final StudentServices services;

    @Autowired
    public handleController(StudentServices services) {
        this.services = services;
    }

    @PostMapping("/register/account")
    public void registerAccount() {

    }

    @PostMapping("/register/course")
    public ResponseEntity<BaseResponse> registerCourse(@RequestBody StudentCourseRegistrationDTO studentCourseRegistration) {
        return new ResponseEntity<>(services.registerStudentToCourse(studentCourseRegistration,"course/register"), HttpStatus.OK);
    }

    @PostMapping("/unregister/course")
    public void unregisterCourse() {

    }

    @PostMapping("/logout/account")
    public void logoutAccount() {

    }
}
