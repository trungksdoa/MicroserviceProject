package com.module.course.enums;

public enum RegistrationUpdateMessages {
    STUDENT_REGISTERED("Student with code %s has registered for the %s course"),
    STUDENT_UNREGISTERED("Student with code %s has left the %s course"),
    EXISTING_REGISTRATION("Student with code %s is already registered for the %s course 123"),
    NON_EXISTING_REGISTRATION("Student with code %s is not registered for the %s course");

    private final String format;

    RegistrationUpdateMessages(String format) {
        this.format = format;
    }

    public String format(Object... args) {
        return String.format(format, args);
    }
}
