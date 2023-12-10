package com.module.course.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentCourseRegistrationDTO {
    @JsonProperty("studentId")
    private String studentId;
    @JsonProperty("studentName")
    private String studentName;
    @JsonProperty("courseId")
    private String courseId;

    @JsonProperty("courseName")
    private String courseName;


// etters and setters...
}
