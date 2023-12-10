package com.module.userservice.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Builder(toBuilder = true)
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_info")
public class Student {
    @Id
    private String studentId;

    private String studentName;

    private String currentCourse;

    private int studentRank;

    private Long accumulatedPoints;

    private int courseCompleted;

    private int totalTeacherReview;

    private String status;
    @Column(name = "dateCreated")
    @CreationTimestamp
    private LocalDateTime dateCreated;


    @Column(name = "dateModified")
    @UpdateTimestamp
    private LocalDateTime dateModified;

}
