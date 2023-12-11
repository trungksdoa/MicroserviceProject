package com.module.course.controller;

import com.module.course.dto.BaseResponse;
import com.module.course.dto.CourseMaterialDTO;
import com.module.course.model.CourseMaterial;
import com.module.course.servicesLogic.services.CourseMaterialServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course/material")
public class CourseMaterialController {


    private final CourseMaterialServices service;

    @Autowired
    public CourseMaterialController(CourseMaterialServices courseMaterialService) {
        this.service = courseMaterialService;
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse> addCourseMaterial(@RequestBody @Valid CourseMaterialDTO courseMaterial) {
        return new ResponseEntity<>(service.doSave(courseMaterial), HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<BaseResponse> modifiedCourseMaterial(@RequestBody CourseMaterialDTO request) {
        return new ResponseEntity<>(service.doUpdate(request), HttpStatus.OK);
    }
}
