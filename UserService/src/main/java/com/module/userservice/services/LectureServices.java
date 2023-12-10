package com.module.userservice.services;

public interface LectureServices {
    // Phương thức trả về môn học của giảng viên
    String getSubject();

    // Phương thức trả về khoa của giảng viên
    String getFaculty();

    // Phương thức trả về xếp hạng của giảng viên
    int getLectureRank();

    // Phương thức trả về tổng số đánh giá của sinh viên cho giảng viên
    int getTotalStudentReview();

    // Phương thức trả về quyền hạn của giảng viên
    String getPermissions();
}
