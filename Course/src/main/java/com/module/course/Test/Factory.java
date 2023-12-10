package com.module.course.Test;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Factory {
    @Autowired
    private MessageSender email;

    @Autowired
    private MessageSender sms;




    public MessageSender action(String type) {
        switch (type) {
            case "email":
                return email;
            case "sms":
                return sms;
        }
        return null;
    }
}
