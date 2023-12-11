package com.module.course.controller;

import com.module.course.ApiVersion;
import com.module.course.dto.BaseResponse;
import com.module.course.dto.CourseDTO;
import com.module.course.restTemplate.RestCall;
import com.module.course.restTemplate.UrlServiceInerface;
import com.module.course.servicesLogic.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Validated
@RequestMapping("/v1/api/course")
public class CourseController {


    private final UrlServiceInerface serviceInerface;
    private final CourseService courseService;
    private final RestTemplate restTemplate;

    @Autowired
    public CourseController(UrlServiceInerface serviceInerface, CourseService courseService, RestTemplate restTemplate) {
        this.serviceInerface = serviceInerface;
        this.courseService = courseService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/test")
    public ResponseEntity<BaseResponse> test() {

        RestCall restCall = new RestCall(serviceInerface,restTemplate);

        BaseResponse rest = restCall.call(HttpMethod.POST, "userService", "hello", ApiVersion.V1, "hello");
        return new ResponseEntity<>(rest, HttpStatus.OK);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<BaseResponse> getCourse(@PathVariable String courseId) {
        return new ResponseEntity<>(courseService.fetchCourseById(courseId), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<BaseResponse> getFullCourse() {
        return new ResponseEntity<>(courseService.fetchAllCourse(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<BaseResponse> createCourse(@RequestBody @Valid CourseDTO course) {
        return new ResponseEntity<>(courseService.doSave(course), HttpStatus.CREATED);
    }

    @PatchMapping()
    public ResponseEntity<BaseResponse> updateCourse(@RequestBody @Valid CourseDTO course) {
        return new ResponseEntity<>(courseService.doUpdate(course), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<BaseResponse> deleteCourse(@RequestBody @Valid CourseDTO course) {
        ;
        return new ResponseEntity<>(courseService.deleteCourse(course), HttpStatus.OK);
    }

}