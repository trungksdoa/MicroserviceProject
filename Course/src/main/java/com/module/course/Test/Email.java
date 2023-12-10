package com.module.course.Test;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("email")
public class Email implements MessageSender {
    @Override
    public void sendMessage() {
        System.out.println("Email sent");
    }

    @Override
    public boolean support() {
        return equals("email");
    }
}
