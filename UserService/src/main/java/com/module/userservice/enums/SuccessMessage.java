package com.module.userservice.enums;

public enum SuccessMessage {

    //Student
    STUDENT_UPDATED_SUCCESS("Student updated successfully"),
    STUDENT_DELETED_SUCCESS("Student deleted successfully"),
    STUDENT_SAVED_SUCCESS("Student saved successfully"),
    STUDENT_FOUND("Student found successfully"),
    STUDENT_LIST_FOUND("Student list found successfully"),

    //Lecture
    LECTURE_UPDATED_SUCCESS("Lecture updated successfully"),
    LECTURE_DELETED_SUCCESS("Lecture deleted successfully"),
    LECTURE_SAVED_SUCCESS("Lecture saved successfully"),
    LECTURE_FOUND("Lecture found successfully"),
    LECTURE_LIST_FOUND("Lecture list found successfully");
    //General message
    private final String message;

    SuccessMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}