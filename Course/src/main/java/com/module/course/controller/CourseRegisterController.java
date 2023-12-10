package com.module.course.controller;


import com.module.course.dto.BaseRespone;
import com.module.course.dto.StudentCourseRegistrationDTO;
import com.module.course.model.StudentCourseRegistration;

import com.module.course.services.CourseHDManagementServices;

import jakarta.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/course/")
public class CourseRegisterController {

    private final CourseHDManagementServices studentService;



    @Autowired
    public CourseRegisterController(CourseHDManagementServices actionManagement) {
        this.studentService = actionManagement;
    }

    @PostMapping("register")
    public ResponseEntity<BaseRespone> enrollCourse(@RequestBody @Valid StudentCourseRegistrationDTO studentRegisterCourse) {

        StudentCourseRegistration registration = StudentCourseRegistration.builder()
                .studentId(studentRegisterCourse.getStudentId())
                .courseId(studentRegisterCourse.getCourseId())
                .studentName(studentRegisterCourse.getStudentName())
                .courseName(studentRegisterCourse.getCourseName())
                .build();

        StudentCourseRegistration data = studentService.registerStudent(registration);

        return new ResponseEntity<>(BaseRespone.builder()
                .message("Student with code " + studentRegisterCourse.getStudentId() + " has left the " + data.getCourseName() + " course")
                .data(data)
                .build(), HttpStatus.OK);
    }

    @GetMapping("unregister/{studentId}/{courseId}")
    public ResponseEntity<BaseRespone> unrollCourse(@PathVariable String studentId, @PathVariable String courseId) {

        studentService.unrollStudent(studentId, courseId);

        return new ResponseEntity<>(BaseRespone.builder()
                .message("Student with code " + studentId + " has left the " + courseId + " course")
                .data(null)
                .build(), HttpStatus.OK);
    }
    //Create singleton

}
