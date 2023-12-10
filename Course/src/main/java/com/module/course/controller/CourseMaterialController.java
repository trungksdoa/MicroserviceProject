package com.module.course.controller;

import com.module.course.Facade.CourseMaterialActionManagement;
import com.module.course.dto.BaseRespone;
import com.module.course.model.CourseMaterial;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course/material")
public class CourseMaterialController {


    private final CourseMaterialActionManagement courseMaterialService;

    @Autowired
    public CourseMaterialController(CourseMaterialActionManagement courseMaterialService) {
        this.courseMaterialService = courseMaterialService;
    }

    @PostMapping("")
    public ResponseEntity<BaseRespone> addCourseMaterial(@RequestBody @Valid CourseMaterial courseMaterial) {
        return new ResponseEntity<>(BaseRespone.builder()
                .message("Add content success")
                .data(courseMaterialService.saveCourseMaterial(courseMaterial))
                .build(), HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<BaseRespone> modifiedCourseMaterial(@PathVariable int id, @RequestBody CourseMaterial request) {
        return new ResponseEntity<>(BaseRespone.builder()
                .message("Modified content success")
                .data(courseMaterialService.modifiedCourseMaterial(id, request))
                .build(), HttpStatus.OK);
    }
}
