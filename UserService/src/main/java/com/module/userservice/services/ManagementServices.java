package com.module.userservice.services;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ManagementServices<T> {
    List<T> fetchAll();

    T fetchById(String id);

    T addNew(T value);

    void removeById(String id);

    T modifyById(String id, T value);

}
