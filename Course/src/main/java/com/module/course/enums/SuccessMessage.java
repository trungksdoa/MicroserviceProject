package com.module.course.enums;

public enum SuccessMessage {

    //Material
    COURSE_MATERIAL_UPDATED_SUCCESS("Course material updated successfully"),
    COURSE_MATERIAL_DELETED_SUCCESS("Course material deleted successfully"),
    COURSE_MATERIAL_SAVED_SUCCESS("Course material saved successfully"),
    COURSE_MATERIAL_FOUND("Course material found successfully"),

    //Course
    COURSE_UPDATED_SUCCESS("Course updated successfully"),
    COURSE_DELETED_SUCCESS("Course deleted successfully"),
    COURSE_SAVED_SUCCESS("Course saved successfully"),
    COURSE_FOUND("Course found successfully"),
    COURSE_LIST_FOUND("Fetching all courses successfully"),


    //Registration
    REGISTRATION_UPDATED_SUCCESS("Registration updated successfully"),
    REGISTRATION_DELETED_SUCCESS("Registration deleted successfully"),
    REGISTRATION_SAVED_SUCCESS("Registration saved successfully"),
    REGISTRATION_FOUND("Registration found successfully"),
    ALL_REGISTRATIONS_FOUND("Fetching all registrations successfully");

    //General message
    private final String message;

    SuccessMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}