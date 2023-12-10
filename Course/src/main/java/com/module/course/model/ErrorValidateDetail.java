package com.module.course.model;

public class ErrorValidateDetail {
    private String field;
    private String value;
    private String location;
    private String issue;
    private String description;

    public ErrorValidateDetail(String field, String value, String location, String issue, String description) {
        this.field = field;
        this.value = value;
        this.location = location;
        this.issue = issue;
        this.description = description;
    }

}
