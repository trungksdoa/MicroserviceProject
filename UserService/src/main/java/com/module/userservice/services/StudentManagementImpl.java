package com.module.userservice.services;


import com.module.userservice.common.Utility;
import com.module.userservice.exception.DataConflicException;
import com.module.userservice.model.Student;
import com.module.userservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.NoSuchElementException;


@Component("studentManagement")
public class StudentManagementImpl implements ManagementServices<Student> {


    private final StudentRepository studentRepository;


    @Autowired
    public StudentManagementImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> fetchAll() {
        List<Student> list = studentRepository.findAll();

        if (list.isEmpty()) {
            throw new NoSuchElementException("No Student Found");
        }

        return list;
    }

    @Override
    public Student fetchById(String id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("No Student Found"));
    }

    @Override
    public Student addNew(Student value) {
        if (this.fetchById(value.getStudentId()) != null) {
            throw new DataConflicException("Student Already Exists");
        }

        return studentRepository.saveAndFlush(value);
    }

    @Override
    public void removeById(String id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student modifyById(String id, Student value) {
        Student oldData = this.fetchById(id);

        if (oldData == null) throw new RuntimeException("No Lecture Found");

        Utility utility = Utility.getInstance();

        Student newData = Student.builder()
                .studentName(utility.getUpdatedValue(value.getStudentName(), oldData.getStudentName()))
                .studentRank(utility.getUpdatedNumberValue(value.getStudentRank(), oldData.getStudentRank()))
                .totalTeacherReview(utility.getUpdatedNumberValue(value.getTotalTeacherReview(), oldData.getTotalTeacherReview()))
                .currentCourse(utility.getUpdatedValue(value.getCurrentCourse(), oldData.getCurrentCourse()))
                .accumulatedPoints(utility.getUpdatedValue(value.getAccumulatedPoints(), oldData.getAccumulatedPoints()))
                .courseCompleted(utility.getUpdatedNumberValue(value.getCourseCompleted(), oldData.getCourseCompleted()))
                .status(utility.getUpdatedValue(value.getStatus(), oldData.getStatus()))
                .build();
        return null;
    }
}
