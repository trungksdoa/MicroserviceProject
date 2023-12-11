package com.module.course.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDTO {
    private String courseId;
    private String courseName;
    private String description;
    private String categoryId;
    private String courseType;
    private String lastModifiedBy;
    private String timeToComplete;
    private String status;

    // getters and setters
}