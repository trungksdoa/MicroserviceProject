package com.module.course.services;


import com.module.course.model.CourseMaterial;
import com.module.course.repositorys.CourseMaterialRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service("courseMaterialService")
@RequiredArgsConstructor
public class MaterialServicesImpl implements BaseMethodServices<CourseMaterial, Integer> {
    private final CourseMaterialRepository contentRepo;

    @Override
    public List<CourseMaterial> get() {
        return contentRepo.findAll();
    }

    @Override
    public Optional<CourseMaterial> get(Integer id) {
        return contentRepo.findById(id);
    }

    @Override
    public CourseMaterial save(CourseMaterial courseMaterial) {
        return contentRepo.saveAndFlush(courseMaterial);
    }


    @Override
    public void delete(CourseMaterial material) {
        contentRepo.delete(material);
    }
}
