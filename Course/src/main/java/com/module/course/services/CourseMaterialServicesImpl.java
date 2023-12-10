package com.module.course.services;


import com.module.course.model.CourseMaterial;
import com.module.course.repositorys.CourseMaterialRepository;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Component("courseMaterialService")
@NoArgsConstructor
public class CourseMaterialServicesImpl implements CourseMaterialServices {
    private CourseMaterialRepository contentRepo;

    @Autowired
    public CourseMaterialServicesImpl(CourseMaterialRepository contentRepo) {
        this.contentRepo = contentRepo;
    }


    @Override
    @SneakyThrows
    public List<CourseMaterial> fetchAllCourseContent() {
        return Optional.of(contentRepo.findAll())
                .filter(course -> !course.isEmpty())
                .orElseThrow(() -> new NoSuchElementException("No material found, try again"));
    }

    @Override
    @SneakyThrows
    public CourseMaterial addNewCourseContent(CourseMaterial courseMaterial) {
        return contentRepo.saveAndFlush(courseMaterial);
    }

    @Override
    @SneakyThrows(RuntimeException.class)
    public CourseMaterial modifiedCourseContent(int id, CourseMaterial request) {
        CourseMaterial courseOption = contentRepo.findById(id).orElseThrow(() -> new NoSuchElementException("No material found, try again"));

        CourseMaterial option = CourseMaterial.builder()
                .content(getUpdateValue(request.getContent(), courseOption.getContent()))
                .lastModifiedBy(getUpdateValue(request.getLastModifiedBy(), courseOption.getLastModifiedBy()))
                .title(getUpdateValue(request.getTitle(), courseOption.getTitle()))
                .image(getUpdateValue(request.getImage(), courseOption.getImage()))
                .build();


        return contentRepo.saveAndFlush(option);

    }

    @Override
    public CourseMaterial fetchCourseContentById(int id) {
        return contentRepo.findCourseMaterialById(id).orElseThrow(() -> new NoSuchElementException("No material found, try again"));
    }

    public <T> T getUpdateValue(T newValue, T oldValue) {
        return newValue != null ? newValue : oldValue;
    }
}
