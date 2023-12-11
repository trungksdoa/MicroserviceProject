package com.module.course.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseMaterialDTO {
    private int materialId;
    private String title;
    private String content;
    private String image;
    private String lastModifiedBy;

    // getters and setters
}