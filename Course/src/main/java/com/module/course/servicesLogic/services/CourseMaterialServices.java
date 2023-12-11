package com.module.course.servicesLogic.services;

import com.module.course.dto.BaseResponse;
import com.module.course.dto.CourseMaterialDTO;
import com.module.course.model.CourseMaterial;


public interface CourseMaterialServices {
    public BaseResponse doSave(CourseMaterialDTO material);
    public BaseResponse doUpdate(CourseMaterialDTO material);
}
