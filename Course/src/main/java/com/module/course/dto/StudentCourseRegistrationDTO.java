package com.module.course.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class StudentCourseRegistrationDTO {
    private String studentId;
    private String studentName;
    private String courseId;
    private String courseName;


// etters and setters...
}
