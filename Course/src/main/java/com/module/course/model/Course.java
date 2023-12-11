package com.module.course.model;

import com.module.course.dto.BaseCourseDTO;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @Column(unique = true, nullable = false, name = "courseId")
    private String courseId;

    @Nonnull
    @Column(name = "courseName")
    private String courseName;

    @Column(name = "description")
    private String description;

    @Nonnull
    @Column(name = "catagoryId")
    private String catagoryId;

    @Column(name = "courseType")
    private String courseType;

    @Column(name = "dateCreated")
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(name = "dateModified")
    @UpdateTimestamp
    private LocalDateTime dateModified;

    //1 month
    private String timeToComplete;

    @Nonnull
    @Column(name = "lastModifiedBy")
    private String lastModifiedBy;

    private String status;


    public BaseCourseDTO toBaseCourseDTO(List<CourseMaterial> materials){
        return BaseCourseDTO.builder()
                .courseId(this.courseId)
                .courseName(this.courseName)
                .description(this.description)
                .catagoryId(this.catagoryId)
//                .totalAttempt(this.totalAttempt)
                .lastModifiedBy(this.lastModifiedBy)
                .courseMaterial(materials)
                .status(this.status)
                .build();
    }
}
