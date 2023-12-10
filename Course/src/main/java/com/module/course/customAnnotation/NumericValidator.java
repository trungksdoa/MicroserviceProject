package com.module.course.customAnnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;


public class NumericValidator implements ConstraintValidator<INumeric, Object> {
    @Override
    public void initialize(INumeric number) {

    }
    @Override
    public boolean isValid(Object input, ConstraintValidatorContext cxt) {
        if (input == null) {
            return false;
        }
        if (input instanceof String) {
            // StringUtils is my helper class
            return StringUtils.isNumeric((String) input);
        }
        return input instanceof Number;
    }
}