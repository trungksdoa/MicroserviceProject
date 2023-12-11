package com.module.userservice.services;

import com.module.userservice.model.Lecture;
import com.module.userservice.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("lectureService")
@RequiredArgsConstructor
public class LectureServiceImpl implements BaseMethodServices<Lecture, String> {

    private final LectureRepository lectureRepository;
    @Override
    public List<Lecture> get() {
        return lectureRepository.findAll();
    }

    @Override
    public Optional<Lecture> get(String id) {
        return lectureRepository.fetchByLectureId(id);
    }

    @Override
    public Lecture save(Lecture value) {
        return lectureRepository.saveAndFlush(value);
    }


    @Override
    public void delete(Lecture lecture) {
        lectureRepository.delete(lecture);
    }

}
