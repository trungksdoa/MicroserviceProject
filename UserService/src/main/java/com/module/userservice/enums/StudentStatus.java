package com.module.userservice.enums;

public enum StudentStatus {
    ACTIVE("Active"),
    NON_ACTIVE("nonActive");
    // Add other statuses here...

    private final String status;

    StudentStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    }