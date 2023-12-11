package com.module.course.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface BaseMethodServices<T,ID> {
    public List<T> get();
    public Optional<T> get(ID id);
    public T save(T t);
    public void delete(T t);
}