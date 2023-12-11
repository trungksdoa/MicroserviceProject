package com.module.userservice.serviceLogic;

import com.module.userservice.serviceLogic.services.LectureServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LectureServicesLogic implements LectureServices {
    @Override
    public String getSubject() {
        return null;
    }

    @Override
    public String getFaculty() {
        return null;
    }

    @Override
    public int getLectureRank() {
        return 0;
    }

    @Override
    public int getTotalStudentReview() {
        return 0;
    }

    @Override
    public String getPermissions() {
        return null;
    }
}
