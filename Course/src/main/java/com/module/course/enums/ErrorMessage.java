package com.module.course.enums;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    //Registration
    REGISTRATION_UPDATED_FAILED("Failed to update registration"),
    REGISTRATION_DELETED_FAILED("Failed to delete registration"),
    REGISTRATION_SAVED_FAILED("Failed to save registration"),
    REGISTRATION_NOT_FOUND("Registration not found"),
    ALL_REGISTRATIONS_NOT_FOUND("Failed to fetch all registrations"),

    //Course
    COURSE_UPDATED_FAILED("Failed to update course"),
    COURSE_DELETED_FAILED("Failed to delete course"),
    COURSE_SAVED_FAILED("Failed to save course"),
    COURSE_NOT_FOUND("Course not found"),
    COURSE_LIST_EMPTY("Course list is empty"),

    //Course material
    COURSE_MATERIAL_SAVED_FAILED("Failed to adding course material because the course already in the server"),
    COURSE_MATERIAL_UPDATED_FAILED("Failed to update course material because the course not exits in the server");
//    COURSE_MATERIAL_NOT_FOUND("Course material not found"),
//    COURSE_MATERIALS_NOT_FOUND("Course materials not found"),
//    COURSE_MATERIALS_NOT_SAVED("Course materials not saved"),
//    COURSE_MATERIALS_NOT_UPDATED("Course materials not updated"),
//    COURSE_MATERIALS_NOT_DELETED("Course materials not deleted"),
//    COURSE_MATERIALS_NOT_FOUND_BY_COURSE("Course materials not found by course"),
//    COURSE_MATERIALS_NOT_FOUND_BY_COURSE_AND_TYPE("Course materials not found by course and type"),
//    COURSE_MATERIALS_NOT_FOUND_BY_TYPE("Course materials not found by type"),
//    COURSE_MATERIALS_NOT_FOUND_BY_COURSE_AND_TITLE("Course materials not found by course and title"),
//    COURSE_MATERIALS_NOT_FOUND_BY_TITLE("Course materials not found by title"),
//    COURSE_MATERIALS_NOT_FOUND_BY_COURSE_AND_DESCRIPTION("Course materials not found by course and description"),
//    COURSE_MATERIALS_NOT_FOUND_BY_DESCRIPTION("Course materials not found by description"),
//    COURSE_MATERIALS_NOT_FOUND_BY_COURSE_AND_FILE("Course materials not found by course and file"),
//    COURSE_MATERIALS_NOT_FOUND_BY_FILE("Course materials not found by file"),
//    COURSE_MATERIALS_NOT_FOUND_BY_COURSE_AND_FILE_NAME("Course materials not found by course and file name"),
//    COURSE_MATERIALS_NOT_FOUND_BY_FILE_NAME("Course materials not found by file name"),
//    COURSE_MATERIAL_NOT_SAVED("Course material not saved"),
//    COURSE_MATERIAL_NOT_UPDATED("Course material not updated"),
//    COURSE_MATERIAL_NOT_DELETED("Course material not deleted"),
//    COURSE_MATERIAL_NOT_FOUND_BY_COURSE("Course material not found by course");


    private final String message;

    ErrorMessage(String format) {
        this.message = format;
    }


}
