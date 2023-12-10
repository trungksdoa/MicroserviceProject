package com.module.course.controller;

import com.module.course.dto.BaseRespone;
import com.module.course.model.Course;
import com.module.course.services.CourseManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/v1/course")
public class CourseController {



    private final CourseManagementService courseService;


    @Autowired
    public CourseController(CourseManagementService courseService) {
        this.courseService = courseService;
    }


    @GetMapping("/{courseId}")
    public ResponseEntity<BaseRespone> getCourse(@PathVariable String courseId,HttpStatus status) {
        return new ResponseEntity<>(BaseRespone.builder()
                .message("Get course successfully by" + " " + courseId)
                .data(courseService.fetchCourseById(courseId))
                .build(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<BaseRespone> getFullCourse() {
        return new ResponseEntity<>(BaseRespone.builder()
                .message("Get all course successfully")
                .data(courseService.fetchAllCourses())
                .build(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<BaseRespone> createCourse(@RequestBody @Valid Course course) {
        return new ResponseEntity<>(BaseRespone.builder()
                .message("Add course successfully")
                .data(courseService.addNewCourse(course))
                .build(), HttpStatus.CREATED);
    }

    @DeleteMapping("")
    public ResponseEntity<BaseRespone> deleteCourse(@RequestBody @Valid Course course) {
        courseService.removeCourseById(course);
        return new ResponseEntity<>(BaseRespone.builder()
                .message("Delete course successfully")
                .data("OK")
                .build(), HttpStatus.OK);
    }

}