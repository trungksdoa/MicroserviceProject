package com.module.course.Test;

import org.springframework.stereotype.Service;

@Service
public interface MessageSender {
    void sendMessage();

    boolean support();
}