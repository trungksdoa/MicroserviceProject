package com.module.userservice.model;

import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {
    private int status;
    private String name;
    private String message;
    private String timeStamp;
    private String path;

    private List<?> details;

    public ErrorResponse(int status, String name, String message) {
        this.status = status;
        this.name = name;
        this.message = message;
    }

    public void setDetails(List<?> details) {
        this.details = details;
    }


// Getter và setter

}
