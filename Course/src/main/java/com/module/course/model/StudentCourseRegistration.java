package com.module.course.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter

@Entity
public class StudentCourseRegistration implements Serializable {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id; // ID của bản ghi
    private String studentId; // ID của sinh viên
    private String studentName; // Tên của sinh viên
    private String courseId; // ID của khóa học
    private String courseName; // Tên của khóa học


    @Column(name = "dateCreated")
    @CreationTimestamp
    private LocalDateTime registrationDate;

    public StudentCourseRegistration() {
    }

    @JsonCreator
    public StudentCourseRegistration(@JsonProperty("id") int id,
                                     @JsonProperty("studentId") String studentId,
                                     @JsonProperty("studentName") String studentName,
                                     @JsonProperty("courseId") String courseId,
                                     @JsonProperty("courseName") String courseName,
                                     @JsonProperty("registrationDate") LocalDateTime registrationDate) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.registrationDate = registrationDate;
    }
}
