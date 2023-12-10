package com.module.course.Facade;


import com.module.course.model.CourseMaterial;
import com.module.course.services.CourseManagementService;
import com.module.course.services.CourseMaterialServices;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseMaterialActionManagement {
    private final CourseMaterialServices courseMaterialService;
    private final CourseManagementService courseManagementService;


    @Autowired
    public CourseMaterialActionManagement(CourseMaterialServices courseMaterialService, CourseManagementService courseManagementService) {
        this.courseMaterialService = courseMaterialService;
        this.courseManagementService = courseManagementService;
    }


    @SneakyThrows
    public CourseMaterial saveCourseMaterial(CourseMaterial courseMaterial) {
        if (!courseManagementService.verifyCourse(courseMaterial.getCourseId())) {
            throw new IllegalAccessException("Course does not available");
        }
        return courseMaterialService.addNewCourseContent(courseMaterial);
    }

    @SneakyThrows
    public CourseMaterial modifiedCourseMaterial(int id, CourseMaterial courseMaterial) {
        if (!courseManagementService.verifyCourse(courseMaterial.getCourseId())) {
            throw new IllegalAccessException("Course does not available");
        }
        return courseMaterialService.modifiedCourseContent(id, courseMaterial);
    }
}
