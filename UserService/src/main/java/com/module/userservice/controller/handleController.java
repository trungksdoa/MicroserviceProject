package com.module.userservice.controller;


import com.module.userservice.dto.StudentCourseRegistrationDTO;
import com.module.userservice.enums.HttpMethods;
import com.module.userservice.model.BaseResponse;
import com.module.userservice.model.StudentCourseRegistration;
import com.module.userservice.restTemplate.RestCall;
import com.module.userservice.restTemplate.UrlServices;
import com.module.userservice.services.StudentServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/student")
public class handleController {

    private final StudentServices studentServices;
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    public handleController(StudentServices studentServices) {
        this.studentServices = studentServices;
    }

    @PostMapping("/register/account")
    public void registerAccount() {

    }

    @PostMapping("/register/course")
    public ResponseEntity<BaseResponse> registerCourse(@RequestBody StudentCourseRegistrationDTO studentCourseRegistration) {

        UrlServices urlServices = new UrlServices(discoveryClient);
        System.out.println(urlServices.getServicesUrl("course"));

        RestCall call = new RestCall(urlServices.getServicesUrl("course"));


        BaseResponse response = call.doCall(HttpMethods.POST, "course/register", studentCourseRegistration);

        return new ResponseEntity<>(response, org.springframework.http.HttpStatus.OK);
    }

    @PostMapping("/unregister/course")
    public void unregisterCourse() {

    }

    @PostMapping("/logout/account")
    public void logoutAccount() {

    }
}
