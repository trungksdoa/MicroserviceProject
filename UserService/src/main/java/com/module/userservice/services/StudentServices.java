package com.module.userservice.services;

public interface StudentServices {
    // Phương thức trả về khóa học hiện tại của sinh viên
    String getCurrentCourse(String studentId);

    String getStudentStatus(String studentId);

    String verifyStudent(String studentId);

    // Phương thức trả về xếp hạng của sinh viên
    int getStudentRank(String studentId);

    // Phương thức trả về điểm tích lũy của sinh viên
    Long getAccumulatedPoints(String studentId);

    // Phương thức trả về tổng số môn học hoàn thành của sinh viên
    int getCourseCompleted(String studentId);

    // Phương thức trả về tổng số đánh giá của giảng viên cho sinh viên
    int getTotalTeacherReview(String studentId);
}
