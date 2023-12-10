package com.module.course.services;

import com.module.course.model.CourseMaterial;

import java.util.List;


public interface CourseMaterialServices {

    public List<CourseMaterial> fetchAllCourseContent();
    public CourseMaterial addNewCourseContent(CourseMaterial courseMaterial);
    public CourseMaterial modifiedCourseContent(int id, CourseMaterial request);

    public CourseMaterial fetchCourseContentById(int id);
//    public Optional<CourseMaterial>  modifiedCourseContent(int id, Map<String, Object> mapValue);


}
