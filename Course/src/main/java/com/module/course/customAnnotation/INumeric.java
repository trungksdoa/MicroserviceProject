package com.module.course.customAnnotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD,PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = NumericValidator.class)
@Documented
public @interface INumeric {
    String message() default "Field must be numeric";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
