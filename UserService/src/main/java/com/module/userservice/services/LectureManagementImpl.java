package com.module.userservice.services;

import com.module.userservice.common.Utility;
import com.module.userservice.model.Lecture;
import com.module.userservice.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component("lectureManagement")
public class LectureManagementImpl implements ManagementServices<Lecture> {

    private final LectureRepository lectureRepository;

    @Autowired
    public LectureManagementImpl(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    @Override
    public List<Lecture> fetchAll() {
        return Optional.of(lectureRepository.findAll()).filter(st -> !st.isEmpty()).orElseThrow(() -> new RuntimeException("No Lecture Found"));
    }

    @Override
    public Lecture fetchById(String id) {
        return lectureRepository.fetchByLectureId(id).orElseThrow(() -> new RuntimeException("No Lecture Found"));
    }

    @Override
    public Lecture addNew(Lecture value) {
        Lecture oldData = lectureRepository.findByLectureId(value.getLectureId());

        if (oldData == null) throw new RuntimeException("No Lecture Found");

        return lectureRepository.saveAndFlush(value);
    }


    @Override
    public void removeById(String id) {
        lectureRepository.deleteById(id);
    }

    @Override
    public Lecture modifyById(String id, Lecture value) {
        Lecture oldData = this.fetchById(id);

        if (oldData == null) throw new RuntimeException("No Lecture Found");

        Utility utility = Utility.getInstance();

        Lecture newData = Lecture.builder()
                .lectureName(utility.getUpdatedValue(value.getLectureName(), oldData.getLectureName()))
                .faculty(utility.getUpdatedValue(value.getFaculty(), oldData.getFaculty()))
                .subject(utility.getUpdatedValue(value.getSubject(), oldData.getSubject()))
                .lectureRank(utility.getUpdatedNumberValue(value.getLectureRank(), oldData.getLectureRank()))
                .totalStudentReview(utility.getUpdatedNumberValue(value.getTotalStudentReview(), oldData.getTotalStudentReview()))
                .permissions(utility.getUpdatedValue(value.getPermissions(), oldData.getPermissions()))
                .status(utility.getUpdatedValue(value.getStatus(), oldData.getStatus()))
                .build();
        return lectureRepository.saveAndFlush(newData);
    }



}
