package com.module.course.dto;


import lombok.*;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseRespone {
    private LocalDateTime timeStamp;
    private String message;
    private Object data;
}
