package com.module.course.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ExceptionRespone {
    private LocalDateTime timeStamp;
    private String message;
    private Object error;
    private String path;
    private HttpStatus status;
}
