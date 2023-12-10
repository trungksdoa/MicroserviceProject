package com.module.course.Test;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("sms")
public class SMS implements MessageSender {
    @Override
    public void sendMessage() {
        System.out.println("SMS sent");
    }

    @Override
    public boolean support() {
        return equals("email");
    }
}
