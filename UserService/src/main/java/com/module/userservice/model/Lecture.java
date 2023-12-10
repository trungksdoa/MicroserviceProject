package com.module.userservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Builder(toBuilder = true)
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Lecture {

    @Id
    private String lectureId;

    private String lectureName;

    private String subject;

    private String faculty;

    private int lectureRank;
    //Tổng số đánh giá của giáo viên
    private int totalStudentReview;

    private String permissions;

    private String status;
    @Column(name = "dateCreated")
    @CreationTimestamp
    private LocalDateTime dateCreated;


    @Column(name = "dateModified")
    @UpdateTimestamp
    private LocalDateTime dateModified;


}
