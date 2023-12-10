package com.module.userservice.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;




@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
public class StudentCourseRegistration {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id; // ID của bản ghi
    private String studentId; // ID của sinh viên
    private String studentName; // Tên của sinh viên
    private String courseId; // ID của khóa học
    private String courseName; // Tên của khóa học


    @Column(name = "dateCreated")
    @CreationTimestamp
    private LocalDateTime registrationDate;
}