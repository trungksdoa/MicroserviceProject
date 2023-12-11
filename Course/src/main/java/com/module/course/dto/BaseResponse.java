package com.module.course.dto;


import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseResponse {
    private LocalDateTime timeStamp;
    private String message;
    private Object data;
}
