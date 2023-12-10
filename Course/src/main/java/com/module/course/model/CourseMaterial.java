package com.module.course.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import static jakarta.persistence.GenerationType.IDENTITY;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CourseMaterial {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    private String title;

    private String content;


    @Column(nullable = true)
    private String image; //


    @CreationTimestamp
    private String dateCreated;

    @UpdateTimestamp
    private String dateModified;

    private String lastModifiedBy;

    private String courseId;

    @PostUpdate
    public void postUpdate() {

    }
}
