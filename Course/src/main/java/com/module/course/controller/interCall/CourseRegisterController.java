package com.module.course.controller.interCall;


import com.module.course.dto.BaseResponse;
import com.module.course.dto.StudentCourseRegistrationDTO;

import com.module.course.servicesLogic.services.CourseRegistrationService;

import jakarta.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/inter/api/course")
public class CourseRegisterController {

    private final CourseRegistrationService studentService;



    @Autowired
    public CourseRegisterController(CourseRegistrationService actionManagement) {
        this.studentService = actionManagement;
    }

    @PostMapping("/register")
    public ResponseEntity<BaseResponse> enrollCourse(@RequestBody @Valid StudentCourseRegistrationDTO course) {
        return new ResponseEntity<>(studentService.register(course), HttpStatus.OK);
    }

    //Create singleton

}
