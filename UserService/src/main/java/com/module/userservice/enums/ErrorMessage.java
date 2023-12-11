package com.module.userservice.enums;

import lombok.Getter;

@Getter
public enum ErrorMessage {


    //Student
    STUDENT_UPDATED_FAILED("Student updated successfully"),
    STUDENT_DELETED_FAILED("Student deleted successfully"),
    STUDENT_SAVED_FAILED("Student saved successfully"),
    STUDENT_NOT_FOUND("Student found successfully"),
    STUDENT_NOT_ACTIVE("Student account is not active, please verify your account"),
    STUDENT_LIST_NOT_FOUND("Student list found successfully"),
    STUDENT_VERIFY_FAILED("Student verify successfully"),

    //Lecture
    LECTURE_UPDATED_FAILED("Lecture updated successfully"),
    LECTURE_DELETED_FAILED("Lecture deleted successfully"),
    LECTURE_SAVED_FAILED("Lecture saved successfully"),
    LECTURE_NOT_FOUND("Lecture found successfully"),
    LECTURE_LIST_NOT_FOUND("Lecture list found successfully");

    private final String message;

    ErrorMessage(String format) {
        this.message = format;
    }


}
