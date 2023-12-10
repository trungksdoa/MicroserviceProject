package com.module.course.customAnnotation;

import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.UUID;

@Aspect
@Component
public class RandomStringIdAspect {

//    @SneakyThrows
//    @Before("@annotation(com.module.course.customAnnotation.GenerateStringId)")
//    public void generateId(JoinPoint joinPoint) {
//
//        System.out.println("RandomStringIdAspect");
//        Object object = joinPoint.getTarget();
//        for (Field field : object.getClass().getDeclaredFields()) {
//            GenerateStringId annotation = field.getAnnotation(GenerateStringId.class);
//            if (annotation != null) {
//                boolean accessible = field.isAccessible();
//                field.setAccessible(true);
//                field.set(object, UUID.randomUUID().toString());
//                field.setAccessible(accessible);
//            }
//        }
//    }
}
