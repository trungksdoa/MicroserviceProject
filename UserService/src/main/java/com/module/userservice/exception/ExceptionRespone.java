package com.module.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
