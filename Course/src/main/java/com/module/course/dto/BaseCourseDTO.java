package com.module.course.dto;

import com.module.course.model.CourseMaterial;
import lombok.*;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaseCourseDTO {

    private String courseId;

    private String courseName;

    private String description;

    private String catagoryId;

//    private String totalAttempt;

    private String lastModifiedBy;

    private List<CourseMaterial> courseMaterial;

    private String status;

}
