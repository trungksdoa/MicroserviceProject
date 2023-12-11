package com.module.course.servicesLogic;

import com.module.course.common.Utils;
import com.module.course.dto.BaseResponse;
import com.module.course.dto.CourseMaterialDTO;
import com.module.course.exception.DataAlreadyExistsException;
import com.module.course.exception.DataNotFoundException;
import com.module.course.model.CourseMaterial;
import com.module.course.services.BaseMethodServices;
import com.module.course.servicesLogic.services.CourseMaterialServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.module.course.enums.ErrorMessage.*;
import static com.module.course.enums.SuccessMessage.*;

@Service("MaterialServiceLogic")
@RequiredArgsConstructor
public class MaterialServicesLogic implements CourseMaterialServices {

    private final BaseMethodServices<CourseMaterial,Integer> courseMaterialServices;

    @Override
    public BaseResponse doSave(CourseMaterialDTO request) {

        CourseMaterial material = CourseMaterial.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .image(request.getImage())
                .lastModifiedBy(request.getLastModifiedBy())
                .build();

        return BaseResponse.builder()
                .message(COURSE_MATERIAL_SAVED_SUCCESS.getMessage())
                .data(courseMaterialServices.save(material))
                .build();
    }


    @Override
    public BaseResponse doUpdate(CourseMaterialDTO request) {

        CourseMaterial material = CourseMaterial.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .image(request.getImage())
                .lastModifiedBy(request.getLastModifiedBy())
                .build();

        Optional<CourseMaterial> oldValue = courseMaterialServices.get(material.getMaterialId());
        if (oldValue.isEmpty()) throw new DataNotFoundException(COURSE_MATERIAL_UPDATED_FAILED.getMessage());
        return BaseResponse.builder()
                .message(COURSE_MATERIAL_UPDATED_SUCCESS.getMessage())
                .data(courseMaterialServices.save(getMaterialUpdate(oldValue.get(), material)))
                .build();
    }


    public CourseMaterial getMaterialUpdate(CourseMaterial oldMaterial, CourseMaterial newMaterial) {
        return CourseMaterial.builder()
                .materialId(oldMaterial.getMaterialId())
                //Wrap all the update value in Utils.getInstance().getUpdateValue
                .title(Utils.getInstance().getUpdateValue(newMaterial.getTitle(), oldMaterial.getTitle()))
                .content(Utils.getInstance().getUpdateValue(newMaterial.getContent(), oldMaterial.getContent()))
                .image(Utils.getInstance().getUpdateValue(newMaterial.getImage(), oldMaterial.getImage()))
                .lastModifiedBy(Utils.getInstance().getUpdateValue(newMaterial.getLastModifiedBy(), oldMaterial.getLastModifiedBy()))
                .build();
    }
}
