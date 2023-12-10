package com.module.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;



@Builder
@Getter
@Setter
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

}
